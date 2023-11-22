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
@Table(name = "Material")
public class Material {

    @Id
    @GeneratedValue
    @Column(name = "material_id")
    private int materialId;

    private String materialName;
    private String description;
    private String unit;
    private int quantity;

    private boolean active = true;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", referencedColumnName = "taskId")
    private Task task;







}