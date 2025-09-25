package edu.cit.wolfe.james.campusequipmentloan.Service;

import edu.cit.wolfe.james.campusequipmentloan.Entities.Student;
import edu.cit.wolfe.james.campusequipmentloan.Repositories.StudentRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public StudentDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found with email: " + email));

        return User.builder()
                .username(student.getEmail())     // login with email
                .password(student.getPassword())  // hashed password
                .roles("STUDENT")                 // default role
                .build();
    }
}
