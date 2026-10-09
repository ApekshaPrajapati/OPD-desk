package com.assessment.opd.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="appointments", indexes=@Index(name="idx_appointment_scheduled_at", columnList="scheduledAt"))
public class Appointment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false, fetch=FetchType.LAZY) @JoinColumn(name="patient_id", nullable=false) private Patient patient;
    @Column(nullable=false, length=100) private String doctorName;
    @Column(nullable=false) private LocalDateTime scheduledAt;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=12) private AppointmentStatus status=AppointmentStatus.SCHEDULED;
    @OneToOne(mappedBy="appointment", cascade=CascadeType.ALL, fetch=FetchType.LAZY) private Consultation consultation;
    protected Appointment() {}
    public Appointment(Patient patient, String doctorName, LocalDateTime scheduledAt) { this.patient=patient; this.doctorName=doctorName; this.scheduledAt=scheduledAt; }
    public Long getId(){return id;} public Patient getPatient(){return patient;} public String getDoctorName(){return doctorName;}
    public LocalDateTime getScheduledAt(){return scheduledAt;} public AppointmentStatus getStatus(){return status;}
    public void complete(){this.status=AppointmentStatus.COMPLETED;}
}
