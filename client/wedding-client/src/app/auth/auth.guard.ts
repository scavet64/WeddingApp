import { Injectable } from "@angular/core";
import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  CanLoad,
  Route,
  UrlSegment,
  Router
} from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";
import { CanActivate } from "@angular/router/src/utils/preactivation";

@Injectable({
  providedIn: "root"
})
export class AuthGuard implements CanActivate {
  path: ActivatedRouteSnapshot[];
  route: ActivatedRouteSnapshot;
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    console.log(this.authService.userIsAuthenticated);
    if (!this.authService.userIsAuthenticated) {
      this.router.navigateByUrl("/auth");
    }
    return this.authService.userIsAuthenticated;
  }
}
