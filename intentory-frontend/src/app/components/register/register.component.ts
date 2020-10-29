import { Component, OnInit } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { AuthService } from "../../service/auth.service";
import { Router } from "@angular/router";
import { MatSnackBar } from '@angular/material';
// import { ToastrService } from 'ngx-toastr';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
})
export class RegisterComponent implements OnInit {
  errorMessage = "temp";
  successMessage = "temp";

  myForm = this.formBuilder.group({
    email: ["", [Validators.required, Validators.email]],
    password: ["", [Validators.required, Validators.minLength(6)]],
    username: ["", [Validators.required]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private snackbar:MatSnackBar
    // private toastr: ToastrService,
  ) {}

  register(formdata) {
    this.authService.register(formdata).subscribe(
      (res) => {
        window.sessionStorage.setItem('isLogged','true');
        console.log("Success" + res);
        this.snackbar.open("Successfully registerd","ok",{duration:2000})
        // this.toastr.success('Addign an admin', 'Addes successfully');
        this.router.navigate(["/dashboard"]);
      },
      (err) => {
        this.snackbar.open("oops !! something is not right","ok",{duration:2000})
        console.log("Failed" + err);
      }
    );
  }

  ngOnInit() {}
}
