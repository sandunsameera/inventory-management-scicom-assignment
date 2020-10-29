import { Component, OnInit } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar } from '@angular/material';
import { Router } from "@angular/router";
import { ProductService } from "src/app/service/product.service";

@Component({
  selector: "app-product-add",
  templateUrl: "./product-add.component.html",
  styleUrls: ["./product-add.component.scss"],
})
export class ProductAddComponent implements OnInit {
  types;
  brands;
  type: String;
  brand: String;
  datePicker: Number;

  minDate = new Date();

  myForm = this.formBuilder.group({
    brand: [null, [Validators.required]],
    type: [null, [Validators.required]],
    expiredate: [null, [Validators.required]],
    price: [, [Validators.required]],
    description: ["", [Validators.required, Validators.minLength(6)]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private _router: Router,
    private snackbar: MatSnackBar
  ) { }

  ngOnInit() {
    this.types = this.productService.types;
    this.brands = this.productService.brands;
  }

  clearForm() {
    this.myForm.reset();
  }

  addProduct(formdata) {
    this.productService
      .addProduct(
        formdata,
        this.type,
        this.brand,
        formdata.expiredate.getTime()
      )
      .subscribe(
        (res) => {

          console.log(res.status.body);
          this.snackbar.open("Product added successfully !!", "ok", { duration: 200 })
          console.log("success");

        },
        (err) => {
          if (err.status == 409) {
            alert("New Product Id is already exist");
          } else {
            this.snackbar.open("oops something is not right !!", "ok", { duration: 200 })
            console.log(err);
          }

        }
      );
  }
}
