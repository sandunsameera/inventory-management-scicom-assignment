import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../app/components/login/login.component';
import {DashboardComponent} from '../app/components/dashboard/dashboard.component';
import { RegisterComponent } from './components/register/register.component';
import { ProductEditComponent } from './components/product-edit/product-edit.component';
import { ProductAddComponent } from './components/product-add/product-add.component';
import { AuthGuard } from './auth.guard';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent,canActivate:[AuthGuard]},
  { path: 'register', component: RegisterComponent },
  { path: 'productedit/:id', component: ProductEditComponent,canActivate:[AuthGuard]   },
  { path: 'productadd', component: ProductAddComponent,canActivate:[AuthGuard]   },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
