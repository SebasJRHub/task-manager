package co.sebdev.task_manager.application.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private LocalDateTime createdAt;
}
