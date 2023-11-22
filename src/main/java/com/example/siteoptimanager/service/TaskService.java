package com.example.siteoptimanager.service;

import com.example.siteoptimanager.dto.TaskDTO;
import com.example.siteoptimanager.model.Material;
import com.example.siteoptimanager.model.Task;
import com.example.siteoptimanager.repository.ProjectRepository;
import com.example.siteoptimanager.repository.TaskRepository;
import com.example.siteoptimanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(int taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }




    public String createTask(TaskDTO taskDTO) {

        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());
        task.setStartDate(taskDTO.getStartDate());
        task.setEstimatedEndDate(taskDTO.getEstimatedEndDate());
        task.setProgress(taskDTO.getProgress());
        task.setActive(taskDTO.isActive());

        task.setUser(userRepository.getReferenceById(taskDTO.getUserId()));





        taskRepository.save(task);

        return "Success";
    }

    public List<Task> getTasksByProjectId(int projectId) {
        return taskRepository.findByUserProjectProjectId(projectId);
    }

    public List<Task> getTasksByUserId(int userId) {
        return taskRepository.findByUserUserId(userId);
    }




    public Task updateTask(int taskId, TaskDTO updatedTaskDTO) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            Task updatedTask = convertToEntity(updatedTaskDTO);

            // Update task properties with the values from updatedTaskDTO
            task.setTaskName(updatedTask.getTaskName());
            task.setDescription(updatedTask.getDescription());
            task.setStartDate(updatedTask.getStartDate());
            task.setEstimatedEndDate(updatedTask.getEstimatedEndDate());
            task.setProgress(updatedTask.getProgress());
            task.setActive(updatedTask.isActive());

            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public void deactivateTask(int taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);

        if (task != null) {
            task.setUser(null);
            task.setActive(false);
            taskRepository.save(task);
        }
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }



    // Helper method to convert TaskDTO to Task entity
    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());
        task.setStartDate(taskDTO.getStartDate());
        task.setEstimatedEndDate(taskDTO.getEstimatedEndDate());
        task.setProgress(taskDTO.getProgress());
        task.setActive(taskDTO.isActive());
        return task;
    }
}
