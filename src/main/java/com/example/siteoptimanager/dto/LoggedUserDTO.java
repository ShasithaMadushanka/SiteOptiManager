package com.example.siteoptimanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserDTO {

    private int userId;

    private String Name;
    private String Role;
    private int ProjectId;




}