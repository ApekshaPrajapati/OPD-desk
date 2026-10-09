package com.assessment.opd.service;

import com.assessment.opd.dto.*;
import com.assessment.opd.model.*;
import com.assessment.opd.repo.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.*;
import java.util.List;

@Service
public class OpdService {
 private final PatientRepository patients; private final AppointmentRepository appointments; private final ConsultationRepository consultations;
 public OpdService(PatientRepository patients, AppointmentRepository appointments, ConsultationRepository consultations) { this.patients=patients; this.appointments=appointments; this.consultations=consultations; }
 @Transactional(readOnly=true) public List<Patient> patients(String search) { return search==null || search.isBlank()?patients.findAll():patients.search(search.trim()); }
 public Patient createPatient(PatientRequest r) { return patients.save(new Patient(r.name().trim(),r.gender(),r.age(),r.phone().trim())); }
 @Transactional(readOnly=true) public List<Appointment> today() { LocalDate d=LocalDate.now(); return appointments.findByScheduledAtGreaterThanEqualAndScheduledAtLessThanOrderByScheduledAtAsc(d.atStartOfDay(),d.plusDays(1).atStartOfDay()); }
 @Transactional(readOnly=true) public List<Appointment> onDate(LocalDate d) { return appointments.findByScheduledAtGreaterThanEqualAndScheduledAtLessThanOrderByScheduledAtAsc(d.atStartOfDay(),d.plusDays(1).atStartOfDay()); }
 public Appointment book(AppointmentRequest r) {
  if(r.scheduledAt().isBefore(LocalDateTime.now().minusMinutes(1))) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Appointment must be in the present or future");
  Patient p=patients.findById(r.patientId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Patient not found"));
  return appointments.save(new Appointment(p,r.doctorName().trim(),r.scheduledAt()));
 }
 @Transactional public Consultation complete(Long id, ConsultationRequest r) {
  Appointment a=appointments.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Appointment not found"));
  if(a.getStatus()==AppointmentStatus.COMPLETED) throw new ResponseStatusException(HttpStatus.CONFLICT,"Consultation is already completed");
  Consultation c=new Consultation(a,r.vitalOneName().trim(),r.vitalOneValue().trim(),r.vitalTwoName().trim(),r.vitalTwoValue().trim(),r.notes()==null?null:r.notes().trim());
  a.complete(); appointments.save(a); return consultations.save(c);
 }
 @Transactional(readOnly=true) public List<Consultation> history(Long patientId) {
  if(!patients.existsById(patientId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Patient not found");
  return consultations.findByAppointmentPatientIdOrderByCompletedAtDesc(patientId);
 }
}
