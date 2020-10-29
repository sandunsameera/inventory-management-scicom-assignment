import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, Subject } from "rxjs";
import { Product } from "../models/product.model";

@Injectable({
  providedIn: "root",
})
export class ProductService {
  constructor(private http: HttpClient) {}

  public selectedProduct: Product;
  newProduct = {} as Product;

  private base_url = "http://localhost:3000/api/products";


  brands = [{ value: "Samsung" }, { value: "LG" }, { value: "TCL" },{ value: "Singer" }];
  types = [{ value: "Fridge" }, { value: "TV" }, { value: "Washing machine" }];

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.base_url);
  }

  getProductById(id: number): Observable<any> {
    return this.http.get(this.base_url +"/" + id);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(this.base_url + "/" + id, {
      responseType: "text",
    });
  }

  updateProduct(formdata, type, brand, date, id) {
    this.newProduct.id = id;
    this.newProduct.price = formdata.price;
    this.newProduct.description = formdata.description;
    this.newProduct.type = type;
    this.newProduct.brand = brand;
    this.newProduct.expireDate = date;
    console.log(this.newProduct);

    console.log(this.base_url + id);
    return this.http.put<any>(this.base_url +"/"+ id, this.newProduct);
  }

  addProduct(formdata, type, brand, date): any {
    this.newProduct.id = Math.floor(Math.random() * 100) + 1 ;
    this.newProduct.price = formdata.price;
    this.newProduct.description = formdata.description;
    this.newProduct.type = type;
    this.newProduct.brand = brand;
    this.newProduct.expireDate = date;

    console.log(this.newProduct);
    return this.http.post<any>(this.base_url, this.newProduct);
  }


  getSearchQuery(query): Observable<Product[]> {
    return this.http.get<Product[]>(
      this.base_url+"?desc=" + query
    );
  }


  getSearchedDataByArray(types: string[]): Observable<Product[]> {
    let params = new HttpParams();
    for (const type of types) {
      params = params.append("types", type);
    }
    return this.http.get<Product[]>( this.base_url+"?types=" +types);
  }

  getSearchedDataByBrandsArray(brands: string[]): Observable<Product[]> {
    let params = new HttpParams();
    for (const brand of brands) {
      params = params.append("types", brand);
    }
    return this.http.get<Product[]>( this.base_url+"?brands=" +brands);
  }
}
