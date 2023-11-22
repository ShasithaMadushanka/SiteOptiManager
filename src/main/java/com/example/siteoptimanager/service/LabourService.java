package com.example.siteoptimanager.service;


import com.example.siteoptimanager.dto.LabourDTO;
import com.example.siteoptimanager.model.Labour;
import com.example.siteoptimanager.repository.LabourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabourService {

    @Autowired
    private LabourRepository labourRepository;

    public List<Labour> getAllLabour() {
        return labourRepository.findAll();
    }

    public Labour getLabourById(int taskId) {
        return labourRepository.findById(taskId).orElse(null);
    }

    public String createLabour(LabourDTO labourDTO) {
        Labour labour = new Labour();
        // Set properties of the 'labour' entity from 'labourDTO'
        labour.setFirstName(labourDTO.getFirstName());
        labour.setLastName(labourDTO.getLastName());
        labour.setCategory(labourDTO.getCategory());
        labour.setActive(labourDTO.isActive());

        // You may set the associated User entity here if needed

        labourRepository.save(labour);

        return "Success";
    }

    public Labour updateLabour(int taskId, LabourDTO updatedLabourDTO) {
        Labour labour = labourRepository.findById(taskId).orElse(null);
        if (labour != null) {
            // Update the 'labour' entity properties from 'updatedLabourDTO'
            labour.setFirstName(updatedLabourDTO.getFirstName());
            labour.setLastName(updatedLabourDTO.getLastName());
            labour.setCategory(updatedLabourDTO.getCategory());
            labour.setActive(updatedLabourDTO.isActive());

            // You may update the associated User entity here if needed

            return labourRepository.save(labour);
        } else {
            return null; // Labour not found
        }
    }

    public void deleteLabour(int taskId) {
        labourRepository.deleteById(taskId);
    }
}
