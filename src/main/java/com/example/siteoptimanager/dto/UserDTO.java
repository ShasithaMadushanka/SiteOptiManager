package com.example.siteoptimanager.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String newPassword;
    private String currentPassword;
    private String confirmPassword;
    private boolean defaultPassword= true;
    private boolean active =true;


    private int roleId;
    private int projectId;




}
