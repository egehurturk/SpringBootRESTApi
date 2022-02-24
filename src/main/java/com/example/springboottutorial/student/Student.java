package com.example.springboottutorial.student;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Student {

    @Id
    @SequenceGenerator(name="student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @EqualsAndHashCode.Exclude
    private long id;

    private String name;
    private String email;
    private LocalDate dob;

    @Transient
    private Integer age;

    public Student() {}

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
}