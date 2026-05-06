package co.sebdev.task_manager.application.service;

import co.sebdev.task_manager.application.dto.ProjectRequest;
import co.sebdev.task_manager.application.dto.ProjectResponse;
import co.sebdev.task_manager.application.dto.UserResponse;
import co.sebdev.task_manager.domain.entity.Project;
import co.sebdev.task_manager.domain.entity.User;
import co.sebdev.task_manager.domain.repository.ProjectRepository;
import co.sebdev.task_manager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .name(project.getName())
                .description(project.getDescription())
                .owner(userService.toResponse(project.getOwner()))
                .members(project.getMembers()
                        .stream()
                        .map(user -> UserResponse.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .name(user.getName())
                                .lastname(user.getLastname())
                                .build())
                        .collect(Collectors.toSet()))
                .createdAt(project.getCreatedAt()).build();

    }

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        User owner = userRepository.findById(projectRequest.getOwnerId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .owner(owner).build();

        projectRepository.save(project);

        return toResponse(project);
    }
}
