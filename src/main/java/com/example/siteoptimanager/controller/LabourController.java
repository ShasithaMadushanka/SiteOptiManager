package com.example.siteoptimanager.controller;

import com.example.siteoptimanager.dto.LabourDTO;
import com.example.siteoptimanager.model.Labour;
import com.example.siteoptimanager.service.LabourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/labour")
public class LabourController {

    private final LabourService labourService;

    @Autowired
    public LabourController(LabourService labourService) {
        this.labourService = labourService;
    }

    @GetMapping("/")
    public List<Labour> getAllLabour() {
        return labourService.getAllLabour();
    }

    @GetMapping("/{taskId}")
    public Labour getLabourById(@PathVariable int taskId) {
        return labourService.getLabourById(taskId);
    }

    @PostMapping("/")
    public String createLabour(@RequestBody LabourDTO labourDTO) {
        return labourService.createLabour(labourDTO);
    }

    @PutMapping("/{taskId}")
    public Labour updateLabour(@PathVariable int taskId, @RequestBody LabourDTO updatedLabourDTO) {
        return labourService.updateLabour(taskId, updatedLabourDTO);
    }

    @DeleteMapping("/{taskId}")
    public void deleteLabour(@PathVariable int taskId) {
        labourService.deleteLabour(taskId);
    }
}
