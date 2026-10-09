import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Appointment, Consultation, Patient } from './models';
@Injectable({ providedIn: 'root' })
export class OpdApiService {
 private http=inject(HttpClient); private base='http://localhost:8080/api';
 patients(search=''){let params=new HttpParams();if(search.trim())params=params.set('search',search.trim());return this.http.get<Patient[]>(`${this.base}/patients`,{params});}
 addPatient(payload:Pick<Patient,'name'|'gender'|'age'|'phone'>){return this.http.post<Patient>(`${this.base}/patients`,payload);}
 appointmentsToday(){return this.http.get<Appointment[]>(`${this.base}/appointments/today`);}
 book(payload:{patientId:number;doctorName:string;scheduledAt:string}){return this.http.post<Appointment>(`${this.base}/appointments`,payload);}
 complete(id:number,payload:{vitalOneName:string;vitalOneValue:string;vitalTwoName:string;vitalTwoValue:string;notes:string}){return this.http.post<Consultation>(`${this.base}/appointments/${id}/consultation`,payload);}
 history(patientId:number){return this.http.get<Consultation[]>(`${this.base}/patients/${patientId}/consultations`);}
}
