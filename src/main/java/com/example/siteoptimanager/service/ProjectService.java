package com.example.siteoptimanager.service;

import com.example.siteoptimanager.model.Project;
import com.example.siteoptimanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public String createProject(Project project) {
        // You can directly save the project using the repository
        projectRepository.save(project);
        return "Project created successfully";
    }

    public Project getProject(int projectId) {
        // Retrieve a project by its projectId using the repository
        return projectRepository.findById(projectId).orElse(null);
    }

    public List<Project> getAllProjects() {
        // Retrieve all projects using the repository
        return projectRepository.findAll();
    }

    public String updateProject(int projectId, Map<String, Object> updatedFields) {
        // Retrieve the project by its ID
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return "Project not found";
        }

        // Update the project fields based on the provided map
        if (updatedFields.containsKey("projectName")) {
            project.setProjectName(updatedFields.get("projectName").toString());
        }
        if (updatedFields.containsKey("location")) {
            project.setLocation(updatedFields.get("location").toString());
        }
        if (updatedFields.containsKey("startDate")) {
            project.setStartDate(updatedFields.get("startDate").toString());
        }
        if (updatedFields.containsKey("completionDate")) {
            project.setCompletionDate(updatedFields.get("completionDate").toString());
        }

        // Save the updated project
        projectRepository.save(project);

        return "Project updated successfully";
    }

    public String inactiveProject(int projectId) {
        // Retrieve the project by its ID
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return "Project not found";
        }

        // Deactivate the project
        project.setActive(false);

        // Save the updated project
        projectRepository.save(project);

        return "Project deactivated successfully";
    }

    public String deleteProject(int projectId) {
        // Delete the project by its ID using the repository
        projectRepository.deleteById(projectId);
        return "Project deleted successfully";
    }
}
