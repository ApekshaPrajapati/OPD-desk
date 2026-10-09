package com.assessment.opd.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="patients", indexes=@Index(name="idx_patient_phone", columnList="phone"))
public class Patient {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=100) private String name;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=10) private Gender gender;
    @Column(nullable=false) private Integer age;
    @Column(nullable=false, length=20) private String phone;
    @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();
    protected Patient() {}
    public Patient(String name, Gender gender, Integer age, String phone) { this.name=name; this.gender=gender; this.age=age; this.phone=phone; }
    public Long getId(){return id;} public String getName(){return name;} public Gender getGender(){return gender;}
    public Integer getAge(){return age;} public String getPhone(){return phone;} public LocalDateTime getCreatedAt(){return createdAt;}
}
