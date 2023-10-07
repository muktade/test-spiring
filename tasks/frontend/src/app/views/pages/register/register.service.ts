import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/types/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private baseUrl: string = 'http://localhost:8089/user/';

  constructor(private http: HttpClient) { }

  public register(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl + 'save'}`, user, {});
  }

}
