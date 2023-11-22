package com.example.siteoptimanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MaterialRequestDTO {
    private String materialName;
    private int quantity;
    private int taskId;
    private int projectId;

}

