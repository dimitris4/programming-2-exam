package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import net.bytebuddy.implementation.bind.annotation.Super;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=20)
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne
    @JsonIgnore
    private Supervisor supervisor;

    public Student() {
    }

    public Student(Long id, String firstName, String lastName, String email, Supervisor supervisor) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.supervisor = supervisor;
    }

    public Student(String firstName, String lastName, String email, Supervisor supervisor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.supervisor = supervisor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    // creates a field in the JSON object with the supervisor's name
    public String getSupervisorName() {
        Supervisor supervisor = this.getSupervisor();
        return supervisor.getFirstName() + " " + supervisor.getLastName();
    }

    // creates a field in the JSON object with the supervisor's id
    public Long getSupervisorId() {
        Supervisor supervisor = this.getSupervisor();
        return supervisor.getId();
    }

    // needed for testing!!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
