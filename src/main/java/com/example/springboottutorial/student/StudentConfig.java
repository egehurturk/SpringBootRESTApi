package com.example.springboottutorial.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo) {
        return args -> {
            Student mariam = new Student("Mariam", "mariam@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5));
            Student alex = new Student("Alex", "alex@gmail.com",
                    LocalDate.of(2001, Month.NOVEMBER, 22));

            repo.saveAll(List.of(mariam, alex));
        };
    }

}
