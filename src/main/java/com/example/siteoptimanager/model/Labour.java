package com.example.siteoptimanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Labour")
public class Labour {

    @Id
    @GeneratedValue
    @Column(name = "labour_id")
    private int labourId;

    private String firstName;
    private String lastName;
    private String category;
    private boolean active = true;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id") // Modify this to the correct column name in your database
    private Project project;




}