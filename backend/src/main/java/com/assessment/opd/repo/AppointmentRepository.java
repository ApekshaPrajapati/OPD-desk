package com.assessment.opd.repo;
import com.assessment.opd.model.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
 @EntityGraph(attributePaths = "patient")
 List<Appointment> findByScheduledAtGreaterThanEqualAndScheduledAtLessThanOrderByScheduledAt(LocalDateTime from, LocalDateTime to);
 @EntityGraph(attributePaths = "patient")
 List<Appointment> findByScheduledAtGreaterThanEqualAndScheduledAtLessThanOrderByScheduledAtAsc(LocalDateTime from, LocalDateTime to);
}
