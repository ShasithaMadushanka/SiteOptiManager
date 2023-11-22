package com.example.siteoptimanager.controller;

import com.example.siteoptimanager.dto.MachineDTO;
import com.example.siteoptimanager.model.Machine;
import com.example.siteoptimanager.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/machines")
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/")
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }

    @GetMapping("/{machineId}")
    public Machine getMachineById(@PathVariable int machineId) {
        return machineService.getMachineById(machineId);
    }

    @PostMapping("/")
    public String createMachine(@RequestBody MachineDTO machineDTO) {
        return machineService.createMachine(machineDTO);
    }

    @PutMapping("/{machineId}")
    public Machine updateMachine(@PathVariable int machineId, @RequestBody MachineDTO updatedMachineDTO) {
        return machineService.updateMachine(machineId, updatedMachineDTO);
    }

    @DeleteMapping("/{machineId}")
    public void deleteMachine(@PathVariable int machineId) {
        machineService.deleteMachine(machineId);
    }

    @PutMapping("/{machineId}/deactivate")
    public void deactivateMachine(@PathVariable int machineId) {
        machineService.deactivateMachine(machineId);
    }
}
