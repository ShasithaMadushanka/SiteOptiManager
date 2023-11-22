package com.example.siteoptimanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDTO {
    private String materialName;
    private String description;
    private String unit;
    private int quantity;
    private boolean active = true;

    private int projectId;
    private int taskId;


}
