import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { NgForm, FormsModule } from "@angular/forms";
import { LoadingController, AlertController } from "@ionic/angular";
import { Observable } from "rxjs";

import { AuthService } from "./auth.service";

@Component({
  selector: "app-auth",
  templateUrl: "./auth.page.html",
  styleUrls: ["./auth.page.scss"]
})
export class AuthPage implements OnInit {
  constructor(
    private authService: AuthService,
    private router: Router,
    private loadingController: LoadingController,
    private formModule: FormsModule,
    private alertCtrl: AlertController
  ) {}

  isLoading = false;
  isLogin = true;

  // constructor(
  //   private authService: AuthService,
  //   private router: Router,
  //   private loadingCtrl: LoadingController,
  //   private alertCtrl: AlertController
  // ) {}

  ngOnInit() {}

  onLogin() {
    this.isLoading = true;
    this.loadingController
      .create({ keyboardClose: true, message: "Logging in" })
      .then(loadingEle => {
        loadingEle.present();
        setTimeout(() => {
          this.isLoading = false;
          loadingEle.dismiss();
          this.router.navigateByUrl("/home");
        }, 1500);
      });
    this.authService.login();
  }

  onRegister() {
    this.isLogin = false;
  }

  // authenticate(email: string, password: string) {
  //   this.isLoading = true;
  //   this.loadingCtrl
  //     .create({ keyboardClose: true, message: 'Logging in...' })
  //     .then(loadingEl => {
  //       loadingEl.present();
  //       let authObs: Observable<AuthResponseData>;
  //       if (this.isLogin) {
  //         authObs = this.authService.login(email, password);
  //       } else {
  //         authObs = this.authService.signup(email, password);
  //       }
  //       authObs.subscribe(
  //         resData => {
  //           console.log(resData);
  //           this.isLoading = false;
  //           loadingEl.dismiss();
  //           this.router.navigateByUrl('/places/tabs/discover');
  //         },
  //         errRes => {
  //           loadingEl.dismiss();
  //           const code = errRes.error.error.message;
  //           let message = 'Could not sign you up, please try again.';
  //           if (code === 'EMAIL_EXISTS') {
  //             message = 'This email address exists already!';
  //           } else if (code === 'EMAIL_NOT_FOUND') {
  //             message = 'E-Mail address could not be found.';
  //           } else if (code === 'INVALID_PASSWORD') {
  //             message = 'This password is not correct.';
  //           }
  //           this.showAlert(message);
  //         }
  //       );
  //     });
  // }

  // onSwitchAuthMode() {
  //   this.isLogin = !this.isLogin;
  // }

  onSubmit(form: NgForm) {
    if (!form.valid) {
      return;
    }
    const email = form.value.email;
    const password = form.value.password;

    console.log(email, password);

    // this.authenticate(email, password);
    // form.reset();
    this.onLogin();
  }

  private showAlert(message: string) {
    this.alertCtrl
      .create({
        header: 'Authentication failed',
        message: message,
        buttons: ['Okay']
      })
      .then(alertEl => alertEl.present());
  }
}