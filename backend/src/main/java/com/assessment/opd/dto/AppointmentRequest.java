package com.assessment.opd.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
public record AppointmentRequest(@NotNull @Positive Long patientId, @NotBlank @Size(max=100) String doctorName, @NotNull LocalDateTime scheduledAt) {}
