package com.example.siteoptimanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MaterialRequest")
public class MaterialRequest {

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private int requestId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id", referencedColumnName = "material_id")
    private Material material;

    private int quantity;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "taskId", referencedColumnName = "taskId")
    private Task task;

    private String status; // Accepted or Rejected
    private String rejectionReason;
    private LocalDateTime requestTime = LocalDateTime.now();
}

