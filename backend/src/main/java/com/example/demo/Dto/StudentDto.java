package com.example.demo.Dto;

/* The difference between the Student entity and the Student dto is that the dto has only the supervisor id as a private field,
* and this is what we want to send from the frontend when a new student is created. */
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long supervisorId;

    public StudentDto() {
    }

    public StudentDto(Long id, String firstName, String lastName, String email, Long supervisorId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.supervisorId = supervisorId;
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

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", supervisorId=" + supervisorId +
                '}';
    }
}
