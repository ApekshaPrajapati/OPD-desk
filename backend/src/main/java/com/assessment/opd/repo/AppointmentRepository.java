package com.assessment.opd.repo;
import com.assessment.opd.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
 List<Appointment> findByScheduledAtGreaterThanEqualAndScheduledAtLessThanOrderByScheduledAt(LocalDateTime from, LocalDateTime to);
 List<Appointment> findByScheduledAtGreaterThanEqualAndScheduledAtLessThanOrderByScheduledAtAsc(LocalDateTime from, LocalDateTime to);
}
