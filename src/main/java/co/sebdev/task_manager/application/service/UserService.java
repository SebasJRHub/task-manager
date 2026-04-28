package co.sebdev.task_manager.application.service;


import co.sebdev.task_manager.application.dto.UserRequest;
import co.sebdev.task_manager.application.dto.UserResponse;
import co.sebdev.task_manager.domain.entity.User;
import co.sebdev.task_manager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .username(userRequest.getUsername())
                .name(userRequest.getName())
                .lastname(userRequest.getLastname())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }
}
