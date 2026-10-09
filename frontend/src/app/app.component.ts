import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OpdApiService } from './opd-api.service';
import { Appointment, Consultation, Gender, Patient } from './models';
@Component({selector:'app-root',standalone:true,imports:[CommonModule,FormsModule],templateUrl:'./app.component.html'})
export class AppComponent implements OnInit {
 private api=inject(OpdApiService); tab:'patients'|'appointments'|'consultations'='patients';
 patients:Patient[]=[];appointments:Appointment[]=[];history:Consultation[]=[];search='';selectedPatientId:number|null=null;selectedAppointmentId:number|null=null;
 message='';error='';todayLabel=new Intl.DateTimeFormat('en-IN',{dateStyle:'medium'}).format(new Date());
 patientForm={name:'',gender:'FEMALE' as Gender,age:0,phone:''};
 appointmentForm={patientId:0,doctorName:'',date:new Date().toISOString().slice(0,10),time:''};
 consultationForm={vitalOneName:'Blood Pressure',vitalOneValue:'',vitalTwoName:'Temperature',vitalTwoValue:'',notes:''};
 ngOnInit(){this.loadPatients();this.loadAppointments();}
 setTab(tab:'patients'|'appointments'|'consultations'){this.tab=tab;this.message='';this.error='';if(tab==='appointments')this.loadAppointments();}
 loadPatients(){this.api.patients(this.search).subscribe({next:rows=>this.patients=rows,error:e=>this.fail(e)});}
 savePatient(){this.clear();this.api.addPatient(this.patientForm).subscribe({next:p=>{this.message=`${p.name} registered`;this.patientForm={name:'',gender:'FEMALE',age:0,phone:''};this.loadPatients();},error:e=>this.fail(e)});}
 loadAppointments(){this.api.appointmentsToday().subscribe({next:rows=>this.appointments=rows,error:e=>this.fail(e)});}
 bookAppointment(){this.clear();const scheduledAt=`${this.appointmentForm.date}T${this.appointmentForm.time}:00`;this.api.book({patientId:+this.appointmentForm.patientId,doctorName:this.appointmentForm.doctorName,scheduledAt}).subscribe({next:()=>{this.message='Appointment booked';this.loadAppointments();},error:e=>this.fail(e)});}
 startConsultation(a:Appointment){this.selectedAppointmentId=a.id;this.selectedPatientId=a.patient.id;this.tab='consultations';this.loadHistory();}
 loadHistory(){if(this.selectedPatientId)this.api.history(this.selectedPatientId).subscribe({next:rows=>this.history=rows,error:e=>this.fail(e)});else this.history=[];}
 completeConsultation(){if(!this.selectedAppointmentId)return;this.clear();this.api.complete(this.selectedAppointmentId,this.consultationForm).subscribe({next:()=>{this.message='Consultation completed';this.selectedAppointmentId=null;this.consultationForm={vitalOneName:'Blood Pressure',vitalOneValue:'',vitalTwoName:'Temperature',vitalTwoValue:'',notes:''};this.loadHistory();this.loadAppointments();},error:e=>this.fail(e)});}
 selectHistoryPatient(id:number|null){this.selectedPatientId=id===null?null:+id;this.loadHistory();}
 private clear(){this.message='';this.error='';}
 private fail(e:any){this.error=e?.error?.message||'Could not reach the OPD API. Check that the backend and MySQL are running.';}
}
