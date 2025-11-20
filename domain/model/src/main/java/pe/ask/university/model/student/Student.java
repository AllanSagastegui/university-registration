package pe.ask.university.model.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Student {
    private UUID id;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthdate;
    private LocalDateTime createdAt;


    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public StudentBuilder toBuilder() {
        return new StudentBuilder().id(this.id).name(this.name).surname(this.surname).dni(this.dni).email(this.email).phone(this.phone).address(this.address).birthdate(this.birthdate).createdAt(this.createdAt);
    }

    public Student() {
    }

    public Student(UUID id, String name, String surname, String dni, String email, String phone, String address, LocalDate birthdate, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
