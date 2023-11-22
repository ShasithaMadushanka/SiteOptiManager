package com.example.siteoptimanager.repository;

import com.example.siteoptimanager.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {


        Material findByMaterialName(String materialName);

        @Query("SELECT m FROM Material m WHERE m.project.projectId = :projectId AND m.active = true")
        List<Material> findAllActiveMaterialsByProjectId(int projectId);

        List<Material> findByTask_TaskId(int taskId);

        Material findByMaterialNameAndProjectProjectId(String materialName, int projectId);

        List<Material> findByProjectProjectId(int projectId);


}







