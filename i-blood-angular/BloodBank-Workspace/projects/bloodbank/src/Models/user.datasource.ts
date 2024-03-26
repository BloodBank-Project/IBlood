import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "./user.model";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class DataSource {
  constructor(private http: HttpClient) { }


  saveUser(User: User): Observable<User> {
    return this.http.post<User>(`http://13.48.82.196:8303/user/save`, User)
  }

  updatePassword(email: string, password: string): Observable<any> {
    return this.http.put<any>('http://13.48.82.196:8303/user/updatePassword', { email, password });
  }

  updateLocation(email: string, bloodBankId: number): Observable<any> {
    return this.http.put<any>('http://13.48.82.196:8303/user/updateLocation', { email, bloodBankId });
  }

  loginUser(email: string, password: string): Observable<any> {
    return this.http.put<any>('http://13.48.82.196:8303/user/login', { email, password });
  }

  fetchAll(): Observable<User[]> {
    return this.http.get<User[]>('http://13.48.82.196:8303/user/all')
  }


  updateBloodGroup(email: string, bloodBankId: number, bloodGroupId: number): Observable<any> {
    return this.http.put<any>('http://13.48.82.196:8303/user/updateLocationAndGroup', { email, bloodBankId, bloodGroupId });
  }


  sendOtp(email: string): Observable<any[]> {
    return this.http.get<any>(`http://13.48.82.196:8303/user/generateOtp?email=${email}`);
  
    }

  verifyOtp(email: string, enteredOtp: string): Observable<any> {
    const params = new HttpParams()
      .set('email', email)
      .set('enteredOtp', enteredOtp);

    const headers = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });

    return this.http.post<any>('http://13.48.82.196:8303/user/verify', null, { headers, params });
  }
}