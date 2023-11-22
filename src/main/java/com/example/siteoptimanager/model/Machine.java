package com.example.siteoptimanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Machine")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machineId")
    private int machineId;  // Changed the field name to match the column name

    private String machineName;
    private String description;
    private String status;

    private boolean active = true;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Machine_Project",  // Define the join table name
            joinColumns = @JoinColumn(name = "machine_id"),  // Column in the 'Machine' table
            inverseJoinColumns = @JoinColumn(name = "project_id")  // Column in the 'Project' table
    )
    private Set<Project> projects;  // Changed to a Set as it's a ManyToMany relationship


}
