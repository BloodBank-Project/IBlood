import { HttpClient } from "@angular/common/http";
import { Patient } from "./patiententity.model";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class DataSourcePatient {
  constructor(private http: HttpClient) {

  }
  savePatientRequest(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(`http://13.48.82.196:8305/patient/save`, patient);
  }

  fetchByPatientId(id: number): Observable<Patient> {
    return this.http.get<Patient>(`http://13.48.82.196:8305/patient/get/${id}`);
  }

  findIdByUserId(id: number): Observable<number> {
    return this.http.get<number>(`http://13.48.82.196:8305/patient/userId/${id}`)
  }
} 