package pe.ask.university.model.student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class StudentBuilder {
    private UUID id;
    private String name;
    private String surname;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private LocalDate birthdate;
    private LocalDateTime createdAt;

    StudentBuilder() {
    }

    public StudentBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public StudentBuilder dni(String dni) {
        this.dni = dni;
        return this;
    }

    public StudentBuilder email(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public StudentBuilder address(String address) {
        this.address = address;
        return this;
    }

    public StudentBuilder birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public StudentBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Student build() {
        return new Student(id, name, surname, dni, email, phone, address, birthdate, createdAt);
    }
}
