package com.assessment.opd.dto;
import jakarta.validation.constraints.*;
public record ConsultationRequest(@NotBlank @Size(max=30) String vitalOneName, @NotBlank @Size(max=50) String vitalOneValue,
 @NotBlank @Size(max=30) String vitalTwoName, @NotBlank @Size(max=50) String vitalTwoValue, @Size(max=1000) String notes) {}
