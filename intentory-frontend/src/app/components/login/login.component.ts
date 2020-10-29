import { Component, OnInit } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { AuthService } from "../../service/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
  errorMessage = "temp";
  successMessage = "temp";

  myForm = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password: ["", [Validators.required, Validators.minLength(6)]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private snackbar:MatSnackBar,
    private router:Router
  ) {}

  login(formdata) {
    const x = this.authService.login(formdata);
    x.then(
      (res) => {
        if (res.status) {
          window.sessionStorage.setItem('isLogged','true');
          this.errorMessage = "temp";
          this.snackbar.open("Login Success ","ok",{duration:2000});
          this.router.navigate(["/dashboard"]);
        } else {
          this.errorMessage = res.error;
          this.snackbar.open("Login Failed check credentials ","ok",{duration:2000});
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/'])
  }

  ngOnInit(): void {
  }
}
