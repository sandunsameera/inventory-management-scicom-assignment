import { Component, OnInit, ViewChild } from "@angular/core";
import { FormBuilder } from "@angular/forms";
import { MatPaginator } from '@angular/material';
import { Router } from "@angular/router";
import { Product } from "src/app/models/product.model";
import { ProductService } from "src/app/service/product.service";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.scss"],
})
export class DashboardComponent implements OnInit {
 
  constructor(
  ) {}

  ngOnInit() {}

 
}
