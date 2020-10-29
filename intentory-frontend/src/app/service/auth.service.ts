import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { User } from "../models/user.model";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private base_url = "http://localhost:3000/api/";

  newuser = {} as User;


  constructor(private http: HttpClient, private router: Router) { }

  login(formData): any {
    try {
      const observable = this.http.post<any>(this.base_url + "login", formData);
      const promise = observable.toPromise();

      return new Promise((resolve, reject) => {
        const returnObject = promise.then(
          (res) => {
            console.log("route to nxt page");
            return { status: true };
          },
          (err) => {
            console.log(err);
            return { error: err.error.message, status: false };
          }
        );
        setTimeout(() => {
          resolve(returnObject);
        }, 500);
      });
    } catch (error) {
      console.log(error);
    }
  }

  register(formData): any {
    this.newuser.userName = formData.username;
    this.newuser.email = formData.email;
    this.newuser.password = formData.password;

    return this.http.post<any>(this.base_url + "register", this.newuser);
  }

  isLoggedIn() {
    window.sessionStorage.getItem('isLogged');
    if (window.sessionStorage.getItem('isLogged') === 'true') {
      return true;
    } else {
      return false;
    }

  }

  logout() {
    window.sessionStorage.setItem('isLogged', 'false');
  }
}
