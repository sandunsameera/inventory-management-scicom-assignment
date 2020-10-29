import { Component, OnInit, ViewChild } from "@angular/core";
import { Product } from "src/app/models/product.model";
import { ProductService } from "src/app/service/product.service";
import { MatDialog } from "@angular/material/dialog";
import { FormBuilder, FormControl } from '@angular/forms';
import { ProductAddComponent } from '../product-add/product-add.component';
import { ProductEditComponent } from '../product-edit/product-edit.component';
import { MatPaginator, MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
})
export class ProductListComponent implements OnInit {
  products: any;
  public errorMsg;

  public remoavbleproduct: Product = {} as Product;

  brands = new FormControl();
  brandList: string[] = ['Samsung', 'TCL', 'Singer', 'LG'];

  types = new FormControl();
  typeList: string[] = ['TV', 'Washing machine', 'Fridge'];

  selectedtypes = [];
  selectedtype;

  selectedbrands = [];
  selectedbrand;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort,{static:true}) sort:MatSort;

  datasource;


  displayedColumns: string[] = [
    "Id",
    "Type",
    "Brand",
    "Price",
    "Description",
    "Expire Date",
    "Action",
  ];

  myForm = this.formBuilder.group({
    query: [null],
  });


  constructor(
    private productService: ProductService,
    public dialog: MatDialog,
    private formBuilder: FormBuilder,
    private snackbar: MatSnackBar,
    private router :Router

  ) { }

  ngOnInit() {
    this.productService.getProducts().subscribe(
      (result) => {
        console.log(result);
        this.products = result;
        this.products = new MatTableDataSource(this.products);
        this.products.paginator = this.paginator;
        this.products.sort = this.sort;
        
      },
      (error) => (this.errorMsg = error)
      );
  }

  removeProduct(remoavbleproduct) {
    this.productService.deleteProduct(remoavbleproduct.id).subscribe(
      (result) => {
        alert("Are you sure to delete "+ remoavbleproduct.brand + " " + remoavbleproduct.type + "?");
        this.snackbar.open("Removed " + remoavbleproduct.brand + " " + remoavbleproduct.type + " Successfully", "ok", { duration: 20000 });
        console.log(result);
        window.location.reload();
      },
      (err) => {
        this.snackbar.open("Error occured", "ok", { duration: 2000 });
        console.log(err);
      }
    );
  }

  postProduct() {
    let ref = this.dialog.open(ProductAddComponent, { width: '600px' },);
    ref.afterClosed().subscribe(
      (res) => {
        window.location.reload();
      }
    )
  }

  editProduct(product) {
    let ref = this.dialog.open(ProductEditComponent, { data: product, width: '600px' });
    ref.afterClosed().subscribe(
      (res) => {
        window.location.reload();
      }
    )
  }


  search(formdata) {
    this.selectedtype = null;
    this.selectedbrand = null;
    if (!this.myForm.valid) {

      return;
    }
    this.productService.getSearchQuery(this.myForm.get('query').value).subscribe(
      (res) => {
        this.products = res;
        this.products = new MatTableDataSource(this.products);
        this.products.paginator = this.paginator;
      }
    )
    console.log(formdata.query);
  }

  onNgBrandModelChange($event) {
    this.myForm.get('query').setValue('');
    this.selectedtype = null;
    this.selectedbrand = $event;
    console.log(this.selectedbrand);
    this.productService.getSearchedDataByBrandsArray(this.selectedbrand).subscribe(
      (res) => {
        this.products = res;
        this.products = new MatTableDataSource(this.products);
        this.products.paginator = this.paginator;
        console.log(res);
      }
    )
  }

  onNgTypeModelChange($event) {
    this.myForm.get('query').setValue('');
    this.selectedbrand = null;
    this.selectedtypes = $event;
    console.log(this.selectedtypes);
    this.productService.getSearchedDataByArray(this.selectedtypes).subscribe(
      (res) => {
        this.products = res;
        this.products = new MatTableDataSource(this.products);
        this.products.paginator = this.paginator;
        console.log(res);
      }
    )
  }

  logout(){
    window.sessionStorage.setItem('isLogged','false');
    this.router.navigate(['/'])
  }
}
