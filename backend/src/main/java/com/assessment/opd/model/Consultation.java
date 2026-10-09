package com.assessment.opd.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="consultations")
public class Consultation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @OneToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="appointment_id", nullable=false, unique=true) private Appointment appointment;
    @Column(nullable=false, length=30) private String vitalOneName;
    @Column(nullable=false, length=50) private String vitalOneValue;
    @Column(nullable=false, length=30) private String vitalTwoName;
    @Column(nullable=false, length=50) private String vitalTwoValue;
    @Column(length=1000) private String notes;
    @Column(nullable=false) private LocalDateTime completedAt=LocalDateTime.now();
    protected Consultation() {}
    public Consultation(Appointment appointment, String vitalOneName, String vitalOneValue, String vitalTwoName, String vitalTwoValue, String notes) {
        this.appointment=appointment; this.vitalOneName=vitalOneName; this.vitalOneValue=vitalOneValue;
        this.vitalTwoName=vitalTwoName; this.vitalTwoValue=vitalTwoValue; this.notes=notes;
    }
    public Long getId(){return id;} public Appointment getAppointment(){return appointment;} public String getVitalOneName(){return vitalOneName;}
    public String getVitalOneValue(){return vitalOneValue;} public String getVitalTwoName(){return vitalTwoName;}
    public String getVitalTwoValue(){return vitalTwoValue;} public String getNotes(){return notes;} public LocalDateTime getCompletedAt(){return completedAt;}
}
