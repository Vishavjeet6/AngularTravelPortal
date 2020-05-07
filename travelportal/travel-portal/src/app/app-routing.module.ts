import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { AuthGuard } from './guard/auth.guard';
import { HomeComponent } from './home/home.component';
import { CoronaComponent } from './corona/corona.component';
import { NewticketComponent } from './newticket/newticket.component';
import { ViewticketComponent } from './viewticket/viewticket.component';
import { TicketdetailsComponent } from './ticketdetails/ticketdetails.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { AdminticketComponent } from './adminticket/adminticket.component';
import { AdminguardGuard } from './guard/adminguard.guard';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'registration', component: RegistrationComponent},
  {path: 'forgot', component: ForgotpasswordComponent},
  {path: 'corona', component: CoronaComponent, canActivate: [AuthGuard]},
  {path: 'newticket', component: NewticketComponent, canActivate: [AuthGuard]},
  {path: 'tickets', component: ViewticketComponent, canActivate: [AuthGuard]},
  {path: 'ticketdetails', component: TicketdetailsComponent, canActivate: [AuthGuard]},
  {path: 'adminlogin', component: AdminloginComponent},
  {path: 'adminhome', component: AdminhomeComponent, canActivate: [AdminguardGuard]},
  {path: 'adminticket', component: AdminticketComponent, canActivate: [AdminguardGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
