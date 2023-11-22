package com.example.siteoptimanager.service;

import com.example.siteoptimanager.dto.MaterialDTO;
import com.example.siteoptimanager.dto.MaterialRequestDTO;
import com.example.siteoptimanager.dto.MaterialRequestResponseDTO;
import com.example.siteoptimanager.model.Material;
import com.example.siteoptimanager.model.MaterialRequest;
import com.example.siteoptimanager.repository.MaterialRepository;
import com.example.siteoptimanager.repository.MaterialRequestRepository;
import com.example.siteoptimanager.repository.ProjectRepository;
import com.example.siteoptimanager.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    MaterialRequestRepository materialRequestRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(int materialId) {
        return materialRepository.findById(materialId).orElse(null);
    }



    public String createMaterial(MaterialDTO materialDTO) {
        Material material = new Material();
        material.setMaterialName(materialDTO.getMaterialName());
        material.setDescription(materialDTO.getDescription());
        material.setUnit(materialDTO.getUnit());
        material.setQuantity(materialDTO.getQuantity());
        material.setProject(projectRepository.getReferenceById(materialDTO.getProjectId()));

        materialRepository.save(material);

        return "Success";
    }

    public String createOrUpdateMaterial(MaterialDTO materialDTO) {
        String materialName = materialDTO.getMaterialName();
        int projectId = materialDTO.getProjectId();

        Material existingMaterial = materialRepository.findByMaterialNameAndProjectProjectId(materialName, projectId);

        if (existingMaterial != null) {
            // Material with the given name and project ID already exists, update quantity
            int newQuantity = existingMaterial.getQuantity() + materialDTO.getQuantity();
            existingMaterial.setQuantity(newQuantity);
            materialRepository.save(existingMaterial);
            return "Material quantity updated";
        } else {
            // Material with the given name and project ID doesn't exist, create a new material
            Material newMaterial = new Material();
            newMaterial.setMaterialName(materialDTO.getMaterialName());
            newMaterial.setDescription(materialDTO.getDescription());
            newMaterial.setUnit(materialDTO.getUnit());
            newMaterial.setQuantity(materialDTO.getQuantity());
            newMaterial.setProject(projectRepository.getReferenceById(projectId));

            materialRepository.save(newMaterial);
            return "New material created";
        }
    }




    public Material updateMaterial(int materialId, Map<String, Object> updatedFields) {
        Optional<Material> materialOptional = materialRepository.findById(materialId);

        if (materialOptional.isPresent()) {
            Material material = materialOptional.get();

            // Update material fields if they exist in the updatedFields map
            if (updatedFields.containsKey("materialName")) {
                material.setMaterialName((String) updatedFields.get("materialName"));
            }
            if (updatedFields.containsKey("description")) {
                material.setDescription((String) updatedFields.get("description"));
            }
            if (updatedFields.containsKey("unit")) {
                material.setUnit((String) updatedFields.get("unit"));
            }
            if (updatedFields.containsKey("quantity")) {
                material.setQuantity((int) updatedFields.get("quantity"));
            }
            if (updatedFields.containsKey("active")) {
                material.setActive((boolean) updatedFields.get("active"));
            }
            // Update more fields as needed

            // Save the updated material
            return materialRepository.save(material);
        } else {
            throw new EntityNotFoundException("Material not found with id: " + materialId);
        }
    }


    public void deleteMaterial(int materialId) {
        materialRepository.deleteById(materialId);
    }

    public void deactivateMaterial(int materialId) {

        Material material = materialRepository.findById(materialId).orElse(null);

        if (material != null) {
            material.setProject(null);
            material.setActive(false);
            materialRepository.save(material);

        }

    }


    public Material getMaterialByName(String materialName) {
        return materialRepository.findByMaterialName(materialName);
    }

    public List<Material> getAllActiveMaterialsByProjectId(int projectId) {
        return materialRepository.findAllActiveMaterialsByProjectId(projectId);
    }

    public List<Material> getMaterialsByTaskId(int taskId) {
        return materialRepository.findByTask_TaskId(taskId);
    }



    public MaterialRequestResponseDTO acceptMaterialRequest(MaterialRequestDTO requestDTO) {
        Material material = materialRepository.findByMaterialName(requestDTO.getMaterialName());

        if (material != null && material.getQuantity() >= requestDTO.getQuantity()) {
            // Material is available, and there is enough quantity
            // Update the material quantity in the database
            int newQuantity = material.getQuantity() - requestDTO.getQuantity();
            material.setQuantity(newQuantity);
            materialRepository.save(material);

            // Create a record of the material request if needed
            // You can add your business logic here

            // Return a response indicating that the request is accepted
            return new MaterialRequestResponseDTO("Accepted", "Request accepted.");
        } else {
            // Material is not available or insufficient quantity
            // Provide a reason for the rejection
            String reason = (material == null) ? "Material not available" : "Insufficient quantity";

            // Return a reject response with a reason
            return new MaterialRequestResponseDTO("Rejected", reason);
        }
    }



    public MaterialRequestResponseDTO requestMaterial(MaterialRequestDTO requestDTO) {
        Material material = materialRepository.findByMaterialNameAndProjectProjectId(
                requestDTO.getMaterialName(), requestDTO.getProjectId());

        if (material != null && material.getQuantity() >= requestDTO.getQuantity()) {
            MaterialRequest materialRequest = new MaterialRequest();
            materialRequest.setMaterial(material);
            materialRequest.setQuantity(requestDTO.getQuantity());
            materialRequest.setTask(taskRepository.findById(requestDTO.getTaskId()).orElse(null));
            materialRequest.setStatus("Accepted");

            // Save the material request
            materialRequestRepository.save(materialRequest);

            // Update material quantity
            int newQuantity = material.getQuantity() - requestDTO.getQuantity();
            material.setQuantity(newQuantity);
            materialRepository.save(material);

            return new MaterialRequestResponseDTO("Accepted", "Material request accepted.");
        } else {
            String reason = (material == null) ? "Material not available" : "Insufficient quantity";
            return new MaterialRequestResponseDTO("Rejected", reason);
        }
    }






}
