import React, { Component } from 'react'
import StudentService from '../services/student-service';
import Select from 'react-select'

class CreateStudentComponent extends Component {
    
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            email: '', 
            supervisorId: '',
            supervisors: [], 
            optionId: '',
            optionName: '',
            errormessage: '',
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleChangeDropDown = this.handleChangeDropDown.bind(this);
        this.saveOrUpdateStudent = this.saveOrUpdateStudent.bind(this);
    }

    componentDidMount() {
        this.getSupervisors();
        if (this.state.id === '_add') {
            return
        } else {
            StudentService.getStudentById(this.state.id).then( (res) =>{
                let student = res.data;
                this.setState({ firstName: student.firstName,
                                lastName: student.lastName,
                                email : student.email,  
                                supervisorId : student.supervisorId, 
                                optionId : student.supervisorId, 
                                optionName: student.supervisorName,
                });
            });
        }        
    }
    
    async getSupervisors() {
        const res = await StudentService.getSupervisors()
        const data = res.data
        const options = data.map(supervisor => ({
          "value" : supervisor.id,
          "label" : supervisor.fullName
        }))
        this.setState({supervisors: options})
    }

    saveOrUpdateStudent = (e) => {
        e.preventDefault();
        let student = {firstName: this.state.firstName, lastName: this.state.lastName, 
                email: this.state.email, supervisorId: this.state.optionId};
        if (this.state.id === '_add') {
            StudentService.createStudent(student)
                .then(response => {
                    return response.data
                })
                .then(data => {
                    console.log(data)
                })
                .catch(error => {
                    this.setState({errormessage: error.response.data.message})
                    console.log(JSON.stringify(this.state.errormessage))
                })
                .then(res => {
                    if (!this.state.errormessage) this.props.history.push('/students');
                });
        } else {
            StudentService.updateStudent(student, this.state.id).then( res => {
                this.props.history.push('/students');
            });
        }
    }
    
    handleInputChange = (event) => {
        this.setState({[event.target.name]: event.target.value})
    }

    handleChangeDropDown = (e) => {
        this.setState({errormessage:''})
        this.setState({ optionId: e.value, optionName: e.label})
    }

    cancel() {
        this.props.history.push('/students');
    }

    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add Student</h3>
        } else {
            return <h3 className="text-center">Update Student</h3>
        }
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6">
                                {
                                    this.getTitle()
                                }
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> First Name: </label>
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.handleInputChange}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.handleInputChange}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email: </label>
                                            <input placeholder="Email Address" name="email" className="form-control" 
                                                value={this.state.email} onChange={this.handleInputChange}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Supervisor: </label>
                                            <div>
                                                <Select options={this.state.supervisors} 
                                                        onChange={this.handleChangeDropDown}
                                                        value={this.state.supervisors.find( obj => {
                                                                    return obj.value === this.state.optionId
                                                                })}
                                                />                  
                                            </div>
                                            <h4>{this.state.errormessage}</h4>
                                        </div>
                                        <button className="btn btn-success" onClick={this.saveOrUpdateStudent}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style = {{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                   </div>
            </div>
        )
    }
}

export default CreateStudentComponent