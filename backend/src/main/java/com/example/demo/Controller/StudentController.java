package com.example.demo.Controller;

import com.example.demo.Dto.StudentDto;
import com.example.demo.Dto.SupervisorDto;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Model.Student;
import com.example.demo.Model.Supervisor;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.SupervisorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController // = @Controller + @ResponseBody, every method returns a domain object instead of a view
@RequestMapping("/api/students")
// More specific: @CrossOrigin(value = "http://localhost:3000")
@CrossOrigin(value = "*")
public class StudentController {

    private StudentService studentService;
    private SupervisorService supervisorService;

    public StudentController(StudentService studentService, SupervisorService supervisorService) {
        this.studentService = studentService;
        this.supervisorService = supervisorService;
    }

    /*
        ResponseEntity represents the whole HTTP response: status code, headers, and body.
    */
    @GetMapping(value="")
    public ResponseEntity<List<Student>> findAllStudents() {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findById(@PathVariable("id") Long id) {
        Student student = studentService.findById(id);
        //this will be caught by the ControllerExceptionHandler
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // refactor
    @PostMapping(value = "")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto) {
        if (studentDto.getSupervisorId() == null) {
            throw new ResourceNotFoundException("Supervisor cannot be null");
        }
        // create new student object and set fields from the dto
        Student newStudent = new Student();
        newStudent.setFirstName(studentDto.getFirstName());
        newStudent.setLastName(studentDto.getLastName());
        newStudent.setEmail(studentDto.getEmail());
        // find supervisor from supervisor id and set it to the new student
        Supervisor supervisor = supervisorService.findById(studentDto.getSupervisorId());
        newStudent.setSupervisor(supervisor);
        // finally, save the new student
        Student savedStudent = studentService.save(newStudent);
        // return the saved student
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        // find the student
        Student student = studentService.findById(id);
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        // find the supervisor and set it to the student
        Supervisor supervisor = supervisorService.findById(studentDto.getSupervisorId());
        student.setSupervisor(supervisor);
        // update the student
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        // find the student
        Student student = studentService.findById(id);
        // delete it
        studentService.delete(student);
        return new ResponseEntity("Student " + student.getId() + " deleted", HttpStatus.OK);
        // alternatively,
        // return ResponseEntity.ok("Student " + student.getId() + " deleted");
    }

    // get all supervisors
    @GetMapping(value="/supervisors")
    public ResponseEntity<List<SupervisorDto>> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorService.findAll();
        // convert the supervisor object to supervisor dtos for the dropdown
        List<SupervisorDto> supervisorDtos = new ArrayList<>();
        // What kind of bigO is that? Answer = O(n)
        // The complexity of this function increases linearly and in direct proportion to the number of inputs.
        for (Supervisor supervisor : supervisors) {
            SupervisorDto supervisorDto = new SupervisorDto();
            supervisorDto.setId(supervisor.getId());
            supervisorDto.setFullName(supervisor.getFirstName() + " " + supervisor.getLastName());
            supervisorDtos.add(supervisorDto);
        }
        return new ResponseEntity<>(supervisorDtos, HttpStatus.OK);
    }
}

    /*// Better way of converting to supervisorDtos with Java 8 Stream and Lambda expression
    // get all supervisors
    @GetMapping(value="/supervisors")
    public ResponseEntity<List<SupervisorDto>> getAllSupervisors() {
        List<Supervisor> supervisors = supervisorService.findAll();
        // convert the supervisor object to supervisor dtos for the dropdown
        List<SupervisorDto> supervisorDtos = supervisors.stream().map( supervisor ->
                new SupervisorDto(supervisor.getId(), supervisor.getFirstName() + " " +
                        supervisor.getLastName())).collect(Collectors.toList());
        return new ResponseEntity<>(supervisorDtos, HttpStatus.OK);
    }*/
