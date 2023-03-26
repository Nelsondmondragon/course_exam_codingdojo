package com.codingdojo.service;

import com.codingdojo.dto.LoginUser;
import com.codingdojo.model.User;
import com.codingdojo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not exists"));
    }



    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not exist."));
    }


    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public void register(User user, BindingResult result) {

        if (result.hasErrors()) {
            return;
        }
        if (this.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "Email exists", "Email exists");
            return;
        }
        if (!user.getPassword().equals(user.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
            return;
        }

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        this.userRepository.save(user);
    }

    public User login(LoginUser newLogin, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        if (!this.existsByEmail(newLogin.getEmail())) {
            result.rejectValue("email", "Email not exists", "The email does not belong to a user.");
            return null;
        }
        User user = this.findByEmail(newLogin.getEmail());
        if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
            return null;
        }
        return user;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
