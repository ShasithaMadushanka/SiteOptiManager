package com.example.siteoptimanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MaterialRequestResponseDTO {
    private String status; // Accepted or Rejected
    private String message;
}

