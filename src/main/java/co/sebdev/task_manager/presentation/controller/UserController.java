package co.sebdev.task_manager.presentation.controller;

import co.sebdev.task_manager.application.dto.UserRequest;
import co.sebdev.task_manager.application.dto.UserResponse;
import co.sebdev.task_manager.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        UserResponse userResponse = userService.findUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username){
        UserResponse userResponse = userService.findUserByUsername(username);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAllUsers(){
        List<UserResponse> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUser(id,userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/{id}/desactivate")
    public ResponseEntity<UserResponse> inactiveUser(@PathVariable Long id){
        UserResponse userResponse = userService.inactiveUser(id);
        return ResponseEntity.ok(userResponse);
    }
}
