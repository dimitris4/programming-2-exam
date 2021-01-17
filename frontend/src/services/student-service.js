import axios from 'axios';

const STUDENT_API_BASE_URL = "http://localhost:8080/api/students";


class StudentService {

    getStudents(){
        return axios.get(STUDENT_API_BASE_URL);
    }

    getSupervisors(){
        return axios.get(STUDENT_API_BASE_URL + '/supervisors');
    }

    createStudent(student){
        return axios.post(STUDENT_API_BASE_URL, student);
    }

    getStudentById(studentId){
        return axios.get(STUDENT_API_BASE_URL + '/' + studentId);
    }

    updateStudent(student, studentId){
        return axios.put(STUDENT_API_BASE_URL + '/' + studentId, student);
    }

    deleteStudent(studentId){
        return axios.delete(STUDENT_API_BASE_URL + '/' + studentId);
    }
}

// exports an object of this class, so we can directly use it inside a component
export default new StudentService()