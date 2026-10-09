export type Gender = 'FEMALE' | 'MALE' | 'OTHER';
export interface Patient { id: number; name: string; gender: Gender; age: number; phone: string; }
export interface Appointment { id: number; patient: Patient; doctorName: string; scheduledAt: string; status: 'SCHEDULED' | 'COMPLETED'; }
export interface Consultation { id: number; appointment: Appointment; vitalOneName: string; vitalOneValue: string; vitalTwoName: string; vitalTwoValue: string; notes: string; completedAt: string; }
