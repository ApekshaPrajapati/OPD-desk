package com.assessment.opd.repo;
import com.assessment.opd.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
 List<Consultation> findByAppointmentPatientIdOrderByCompletedAtDesc(Long patientId);
}
