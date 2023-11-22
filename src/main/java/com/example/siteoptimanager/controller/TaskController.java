package com.example.siteoptimanager.controller;

import com.example.siteoptimanager.dto.TaskDTO;
import com.example.siteoptimanager.model.Task;
import com.example.siteoptimanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskList")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable int taskId) {
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProjectId(@PathVariable int projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    @GetMapping("/tasksByUser/{userId}")
    public List<Task> getTasksByUserId(@PathVariable int userId) {
        return taskService.getTasksByUserId(userId);
    }

    @PostMapping("/registration")
    public String createUser(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable int taskId, @RequestBody TaskDTO updatedTaskDTO) {
        return taskService.updateTask(taskId, updatedTaskDTO);
    }

    @PutMapping("/inactive/{taskId}")
    public void deactivateTask(@PathVariable int taskId) {
        taskService.deactivateTask(taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
    }


}
