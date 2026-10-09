package com.assessment.opd.api;
import com.assessment.opd.dto.*;
import com.assessment.opd.model.*;
import com.assessment.opd.service.OpdService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController @RequestMapping("/api/appointments")
public class AppointmentController {
 private final OpdService service; public AppointmentController(OpdService service){this.service=service;}
 @GetMapping("/today") public List<Appointment> today(){return service.today();}
 @GetMapping public List<Appointment> onDate(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date){return service.onDate(date);}
 @PostMapping public Appointment book(@Valid @RequestBody AppointmentRequest request){return service.book(request);}
 @PostMapping("/{id}/consultation") public Consultation complete(@PathVariable Long id,@Valid @RequestBody ConsultationRequest request){return service.complete(id,request);}
}
