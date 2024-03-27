import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { DonorDetails } from "./donorModel";
import { PatientRequest } from "./patientModel";
import { BloodBank } from "./bloodBankModel";

@Injectable({ providedIn: 'root' })
export class DataSource {

  constructor(private http: HttpClient) { }

  getCountDonations(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://13.48.82.196:8304/details/count/donations')
  }

  getCountRequests(): Observable<PatientRequest[]> {
    return this.http.get<PatientRequest[]>('http://13.48.82.196:8305/patientRequest/count/requests')
  }

  getTotalAcceptedStatuses(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://13.48.82.196:8304/details/total/accepted')
  }

  getTotalRejectedStatuses(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://13.48.82.196:8304/details/total/rejected')
  }

  getTotalPendingStatuses(): Observable<DonorDetails[]> {
    return this.http.get<DonorDetails[]>('http://13.48.82.196:8304/details/total/pending')
  }

  getTotalBloodBanks(): Observable<BloodBank[]> {
    return this.http.get<BloodBank[]>('http://13.48.82.196:8304/details/total/bloodBanks')
  }
}