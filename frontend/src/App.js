import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListStudentComponent from './components/ListStudentComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateStudentComponent from './components/CreateStudentComponent';
import ViewStudentComponent from './components/ViewStudentComponent';

// root component 
function App() {
  return (
    <div>
        <Router> 
              <HeaderComponent />
                <div className="container">
                    <Switch> 
                          {/*when we enter http://localhost:3000/  the list component will be rendered 
                          Then you have to put exact keyword to the Route which it's path is also included by another Route's path.*/}
                          {/* the Router maintains a history object which has all the mappings of the routings */ }
                          {/* the Router passes the history object to the components as props */ }
                          {/* path as an array */}
                          <Route path = "/" exact component = {ListStudentComponent}></Route>
                          <Route path = "/students" component = {ListStudentComponent}></Route>
                          <Route path = "/add-student/:id" component = {CreateStudentComponent}></Route>
                          <Route path = "/view-student/:id" component = {ViewStudentComponent}></Route>
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
  );
}

export default App;