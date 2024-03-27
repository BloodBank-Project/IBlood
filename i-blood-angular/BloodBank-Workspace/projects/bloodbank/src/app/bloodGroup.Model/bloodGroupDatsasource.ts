import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodGroup } from './bloodGroupModel';

@Injectable({ providedIn: 'root' })
export class DataSource {
  constructor(private http: HttpClient) { }

  getAllBloodGroups(): Observable<BloodGroup[]> {
    return this.http.get<BloodGroup[]>('http://13.48.82.196:/bloodbank/all');
  }

}