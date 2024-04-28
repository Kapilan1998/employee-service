package com.connectMicroService.employeeservice.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity     //marks the class as a JPA entity, indicating that instances of this class will be managed by JPA (Java Persistence API)
@Table(name ="employee")        // Specifies the name of the database table to which this entity is mapped.
@Data       // define getters , setters
public class Employee {

    @Id     //primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // database generates the primary key values automatically(how DB generates primary key)
    @Column(name= "id")     // column name in the database
    private int id;         // attributes of employee class
    @Column(name= "name")
    private String name;        // attributes of employee class
    @Column(name= "email")
    private String email;       // attributes of employee class
    @Column(name= "blood_group")
    private String bloodGroup;      // attributes of employee class
}
