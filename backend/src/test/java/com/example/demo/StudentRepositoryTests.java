package com.example.demo;

import com.example.demo.Model.Student;
import com.example.demo.Model.Supervisor;
import com.example.demo.Repository.StudentRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.w3c.dom.ls.LSOutput;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    public void init(){
        studentRepository.deleteAll();
    }

    @Test
    public void should_find_no_students_if_repository_is_empty() {
        Iterable<Student> students = studentRepository.findAll();
        assertThat(students).isEmpty();
    }

    @Test
    public void should_save_a_student() {
        // create and save a supervisor first
        Supervisor supervisor = new Supervisor("John", "Nielsen", "johnn@kea.com");
        entityManager.persist(supervisor);
        // create and save a student
        Student student = new Student(1L, "Dimitrios", "Gkiokas", "ronaldd@kea.com", supervisor);
        Student savedStudent = studentRepository.save(student);
        // assert that the saved student is the same as the created student
        System.out.println("saved student=  " + savedStudent);
        System.out.println("student=  " + student);
        assertEquals(savedStudent, student);
    }

    @Test
    public void should_find_all_students() {
        // create and save a supervisor first
        Supervisor supervisor = new Supervisor("John", "Nielsen", "johnn@kea.com");
        entityManager.persist(supervisor);
        // create and save two students
        Student student1 = new Student("Dimitrios", "Gkiokas", "dimitriosg@kea.com", supervisor);
        Student student2 = new Student("Ilias", "Martidis", "iliasm@kea.com", supervisor);
        entityManager.persist(student1);
        entityManager.persist(student2);
        // find the saved students and assert that the list contains one of them
        Iterable<Student> students = studentRepository.findAll();
        System.out.println("print student list= " + students);
        assertThat(students).hasSize(2).contains(student1);
    }

}
