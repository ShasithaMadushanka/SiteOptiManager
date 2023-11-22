package com.example.siteoptimanager.controller;

import com.example.siteoptimanager.dto.MaterialDTO;
import com.example.siteoptimanager.dto.MaterialRequestDTO;
import com.example.siteoptimanager.dto.MaterialRequestResponseDTO;
import com.example.siteoptimanager.model.Material;
import com.example.siteoptimanager.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/all")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{materialId}")
    public Material getMaterialById(@PathVariable int materialId) {
        return materialService.getMaterialById(materialId);
    }

    @GetMapping("/active/{projectId}")
    public List<Material> getAllActiveMaterialsByProjectId(@PathVariable int projectId) {
        return materialService.getAllActiveMaterialsByProjectId(projectId);
    }

    @GetMapping("/byTask/{taskId}")
    public List<Material> getMaterialsByTaskId(@PathVariable int taskId) {
        return materialService.getMaterialsByTaskId(taskId);
    }


    @PostMapping("/addMaterials")
    public String createMaterial(@RequestBody MaterialDTO materialDTO) {
        return materialService.createMaterial(materialDTO);
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdateMaterial(@RequestBody MaterialDTO materialDTO) {
        return materialService.createOrUpdateMaterial(materialDTO);
    }



    @PostMapping("/request")
    public ResponseEntity<MaterialRequestResponseDTO> requestMaterial(@RequestBody MaterialRequestDTO requestDTO) {
        MaterialRequestResponseDTO response = materialService.requestMaterial(requestDTO);
        if ("Accepted".equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/acceptRequest")
    public ResponseEntity<MaterialRequestResponseDTO> acceptMaterialRequest(@RequestBody MaterialRequestDTO requestDTO) {
        MaterialRequestResponseDTO response = materialService.acceptMaterialRequest(requestDTO);
        if ("Accepted".equals(response.getStatus())) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }







    @PutMapping("/{materialId}")
    public ResponseEntity<Material> updateMaterial(@PathVariable int materialId, @RequestBody Map<String, Object> updatedFields) {
        try {
            Material updatedMaterial = materialService.updateMaterial(materialId, updatedFields);
            return new ResponseEntity<>(updatedMaterial, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @DeleteMapping("/{materialId}")
    public void deleteMaterial(@PathVariable int materialId) {
        materialService.deleteMaterial(materialId);
    }

    @PutMapping("inactive/{materialId}")
    public void deactivateMaterial(@PathVariable int materialId) {
        materialService.deactivateMaterial(materialId);
    }






}
