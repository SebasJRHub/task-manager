package co.sebdev.task_manager.application.dto;

import co.sebdev.task_manager.domain.entity.TaskPriority;
import co.sebdev.task_manager.domain.entity.TaskStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private UserResponse asignee;
}
