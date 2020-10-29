import { Component, OnInit,Inject } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { MatSnackBar, MAT_DIALOG_DATA } from '@angular/material';
import { ActivatedRoute, Router } from "@angular/router";
import { Product } from "src/app/models/product.model";
import { ProductService } from "src/app/service/product.service";

@Component({
  selector: "app-product-edit",
  templateUrl: "./product-edit.component.html",
  styleUrls: ["./product-edit.component.scss"],
})
export class ProductEditComponent implements OnInit {
  brand: String;
  type: String;
  types;
  brands;
  // editProduct;

  minDate = new Date();
  productData:Product;

  myForm = this.formBuilder.group({
    type:[null,[Validators.required]],
    brand:[null,[Validators.required]],
    expiredate: [null,[Validators.required]],
    price: [null, [Validators.required]],
    description: [null, [Validators.required, Validators.minLength(6)]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private productService: ProductService,
    @Inject (MAT_DIALOG_DATA) public data:any,
    private snackbar:MatSnackBar
  ) {}
  editProduct = this.productService.selectedProduct;

  newProduct: Product;

  updateProduct(formdata) {
    this.productService
      .updateProduct(
        formdata,
        this.type,
        this.brand,
        formdata.expiredate.getTime(),
        this.data.id
      )
      .subscribe(
        (res) => {
          console.log("success");
          this.snackbar.open("Product edited successfully !!","ok",{duration:200});
          console.log(res);
        },
        (err) => {
          this.snackbar.open("oops something is not right !!","ok",{duration:200});
          console.log(err);
        }
      );
  }

  ngOnInit() {
    this.brands = this.productService.brands;
    this.types = this.productService.types;
    this.productService
      .getProductById(this.data.id)
      .subscribe((res) => {
        this.productData = res;
      });


  }
  
}
