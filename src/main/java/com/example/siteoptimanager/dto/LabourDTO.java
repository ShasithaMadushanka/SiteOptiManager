package com.example.siteoptimanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabourDTO {
    private String firstName;
    private String lastName;
    private String category;
    private boolean active;


}
