package com.example.siteoptimanager.repository;

import com.example.siteoptimanager.model.Material;
import com.example.siteoptimanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {


    List<Task> findByUserProjectProjectId(int projectId);

    List<Task> findByUserUserId(int userId);



}
