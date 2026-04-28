package co.sebdev.task_manager.application.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String name;
    private String lastname;

}
