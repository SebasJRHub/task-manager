package co.sebdev.task_manager.presentation.controller;

import co.sebdev.task_manager.application.dto.UserRequest;
import co.sebdev.task_manager.application.dto.UserResponse;
import co.sebdev.task_manager.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }
}
