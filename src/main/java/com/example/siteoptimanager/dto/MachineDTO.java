package com.example.siteoptimanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineDTO {
    private String machineName;
    private String description;
    private String status;
    private boolean active;

    private int labourId;


}
