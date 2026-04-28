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
public class TaskRequest {

    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime dueDate;
}
