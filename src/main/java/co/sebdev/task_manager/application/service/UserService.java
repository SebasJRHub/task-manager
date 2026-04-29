package co.sebdev.task_manager.application.service;


import co.sebdev.task_manager.application.dto.UserRequest;
import co.sebdev.task_manager.application.dto.UserResponse;
import co.sebdev.task_manager.domain.entity.User;
import co.sebdev.task_manager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Este metodo me ayuda a reciclar los metodos que devuelven un *UserResponse*
    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();
    }

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

        return toResponse(user);
    }

    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toResponse(user);
    }

    public UserResponse findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(("User not found")));

        return toResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userRequest.getUsername() != null) {
            if (userRepository.existsByUsername(userRequest.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
            user.setUsername(userRequest.getUsername());
        }

        if(userRequest.getEmail() != null){
            user.setEmail(userRequest.getEmail());
        }

        if(userRequest.getName() != null){
            user.setName(userRequest.getName());
        }

        if(userRequest.getLastname() != null){
            user.setLastname(userRequest.getLastname());
        }

        userRepository.save(user);

        return toResponse(user);
    }

    public List<UserResponse> findAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse inactiveUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        user.setActive(false);

        userRepository.save(user);

        return toResponse(user);
    }
}
