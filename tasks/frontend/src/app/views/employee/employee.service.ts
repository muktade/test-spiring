import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/types/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl: string = 'http://localhost:8089/user/';

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl + 'list/1,2,3'}`);
  }

}
