package com.example.springboottutorial.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent())
                throw new IllegalStateException("email taken");

        studentRepository.save(student);
    }

    public void deleteStudentFromId(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("student with id " + id + " does not exist");
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudentWithId(Long studentId, String name, String mail) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if (checkName(student, name))
            student.setName(name);

        if (checkMail(student, mail)) {
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(mail);
            if (studentByEmail.isPresent())
                throw new IllegalStateException("email taken");
            student.setEmail(mail);
        }

    }

    private boolean checkName(Student student, String name) {
        return name != null && name.length() > 0 && !student.getName().equals(name);
    }

    private boolean checkMail(Student student, String mail) {
        return mail != null && mail.length() > 0 && !student.getEmail().equals(mail);
    }
}
