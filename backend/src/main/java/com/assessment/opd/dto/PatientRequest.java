package com.assessment.opd.dto;
import com.assessment.opd.model.Gender;
import jakarta.validation.constraints.*;
public record PatientRequest(@NotBlank @Size(max=100) String name, @NotNull Gender gender,
 @NotNull @Min(0) @Max(130) Integer age, @NotBlank @Pattern(regexp="[0-9+() -]{7,20}") String phone) {}
