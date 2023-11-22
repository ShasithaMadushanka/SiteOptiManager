package com.example.siteoptimanager.service;

import com.example.siteoptimanager.dto.MachineDTO;
import com.example.siteoptimanager.model.Machine;
import com.example.siteoptimanager.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public Machine getMachineById(int machineId) {
        return machineRepository.findById(machineId).orElse(null);
    }

    public String createMachine(MachineDTO machineDTO) {
        Machine machine = new Machine();
        machine.setMachineName(machineDTO.getMachineName());
        machine.setDescription(machineDTO.getDescription());
        machine.setStatus(machineDTO.getStatus());
        machine.setActive(machineDTO.isActive());

        machineRepository.save(machine);

        return "Success";
    }

    public Machine updateMachine(int machineId, MachineDTO updatedMachineDTO) {
        Machine machine = machineRepository.findById(machineId).orElse(null);
        if (machine != null) {
            machine.setMachineName(updatedMachineDTO.getMachineName());
            machine.setDescription(updatedMachineDTO.getDescription());
            machine.setStatus(updatedMachineDTO.getStatus());
            machine.setActive(updatedMachineDTO.isActive());

            return machineRepository.save(machine);
        } else {
            return null;
        }
    }

    public void deleteMachine(int machineId) {
        machineRepository.deleteById(machineId);
    }

    public void deactivateMachine(int machineId) {
        Machine machine = machineRepository.findById(machineId).orElse(null);
        if (machine != null) {
            machine.setActive(false);
            machineRepository.save(machine);
        }
    }
}
