import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CardComponent} from './pages/card/card.component';
import {LoginComponent} from './pages/login/login.component';
import {SignupComponent} from './pages/signup/signup.component';
import { ProductDetailComponent } from './pages/product-detail/product-detail.component';
import {CartComponent} from './pages/cart/cart.component';
import {AuthGuard} from "./_guards/auth.guard";
// import {OrderComponent} from "./pages/order/order.component";
// import {OrderDetailComponent} from "./pages/order-detail/order-detail.component";
import { ProductListComponent } from './pages/product-list/product-list.component';
// import {UserDetailComponent} from "./pages/user-edit/user-detail.component";

import {Role} from "./enum/Role";



const routes: Routes = [
  {path: '', redirectTo: '/product', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LoginComponent},
  {path: 'register', component: SignupComponent},
  {path: 'success', component: SignupComponent},

  {path: '', redirectTo: '/product', pathMatch: 'full'},
  {path: 'product/:id', component: ProductDetailComponent},
  {path: 'category/:id', component: CardComponent},
  {path: 'product', component: CardComponent},
  {path: 'category', component: CardComponent},

  {path: 'cart', component: CartComponent},
  {path: 'success', component: SignupComponent},
  // {path: 'order/:id', component: OrderDetailComponent, canActivate: [AuthGuard]},
  // {path: 'order', component: OrderComponent, canActivate: [AuthGuard]},
  {path: 'seller', redirectTo: 'seller/product', pathMatch: 'full'},
  {
      path: 'seller/product',
      component: ProductListComponent,
      canActivate: [AuthGuard],
      data: {roles: [Role.Manager, Role.Employee]}
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
