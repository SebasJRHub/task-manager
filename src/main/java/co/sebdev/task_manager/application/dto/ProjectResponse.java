package co.sebdev.task_manager.application.dto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private UserResponse owner;
    private Set<UserResponse> members;
    private LocalDateTime createdAt;

}
