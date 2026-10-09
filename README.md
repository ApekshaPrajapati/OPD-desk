# OPD Mini Module

A small demo-ready OPD workflow for patient registration, appointments, and consultation summaries.

## Stack

- Backend: Java 17, Spring Boot 3, Spring Data JPA, Bean Validation
- Database: MySQL 8
- Frontend: Angular 17 standalone components and HttpClient

## Features

- Register patients and list/search by name or phone
- Book appointments for a patient and doctor
- List appointments scheduled for today
- Record two vitals plus notes, complete a consultation, and view completed consultations by patient

## Run locally

1. Create a MySQL database or use the provided Docker Compose file: `docker compose up -d`.
2. Start backend: `cd backend && mvn spring-boot:run`. API defaults to `http://localhost:8080/api`.
3. Start Angular: `cd frontend && npm install && npm start`. UI defaults to `http://localhost:4200`.

MySQL defaults are configured in `backend/src/main/resources/application.properties` and can be overridden using `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

### Quick demo without Docker/MySQL

Run `cd backend && mvn spring-boot:run -Dspring-boot.run.profiles=demo`. This uses an in-memory H2 database and resets data when the backend stops. It is only for a quick local demo; the default profile remains configured for MySQL.

## Functional flow to explain in review

1. Patient screen submits a validated patient form to `POST /api/patients`; the list and search use `GET /api/patients`.
2. Appointment screen selects a patient and sends doctor/date-time to `POST /api/appointments`; today's list uses `GET /api/appointments/today`.
3. Consultation screen opens a scheduled appointment and posts vitals/notes to `POST /api/appointments/{id}/consultation`. The backend marks it completed in the same transaction. Patient history uses `GET /api/patients/{id}/consultations`.

The application uses a compact layered design: REST controllers → services → Spring Data repositories → MySQL entities. No login/RBAC is included because it is optional and outside the core demo flow.

## API overview

- `GET /api/patients?search=` / `POST /api/patients`
- `GET /api/appointments/today` / `GET /api/appointments?date=YYYY-MM-DD` / `POST /api/appointments`
- `POST /api/appointments/{id}/consultation`
- `GET /api/patients/{id}/consultations`

For the limited assessment window, this is intentionally a single-module backend and a compact three-tab Angular UI. For a production system, add authentication, audit logging, stronger validation, migrations, and role controls.
