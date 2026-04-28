package co.sebdev.task_manager.application.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    private String name;
    private String description;
}
