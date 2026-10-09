package com.assessment.opd.api;
import com.assessment.opd.dto.PatientRequest;
import com.assessment.opd.model.*;
import com.assessment.opd.service.OpdService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/patients")
public class PatientController {
 private final OpdService service; public PatientController(OpdService service){this.service=service;}
 @GetMapping public List<Patient> list(@RequestParam(required=false) String search){return service.patients(search);}
 @PostMapping public Patient create(@Valid @RequestBody PatientRequest request){return service.createPatient(request);}
 @GetMapping("/{id}/consultations") public List<Consultation> history(@PathVariable Long id){return service.history(id);}
}
