import { Component, ViewChild } from "@angular/core";
import { ModalController, IonInfiniteScroll } from "@ionic/angular";
import { UploadComponent } from "./upload/upload.component";
import { ImageResponse } from "./image-response";

@Component({
  selector: "app-home",
  templateUrl: "home.page.html",
  styleUrls: ["home.page.scss"]
})
export class HomePage {
  @ViewChild(IonInfiniteScroll) infiniteScroll: IonInfiniteScroll;

  constructor(private modalCtrl: ModalController) {}

  private tmpPepe = new ImageResponse(
    "https://i.kym-cdn.com/photos/images/newsfeed/001/460/439/32f.jpg",
    "person",
    "comment",
    "PepeLaugh"
  );

  public coolImages: ImageResponse[] = [this.tmpPepe, this.tmpPepe];

  onUpload() {
    this.modalCtrl
      .create({ component: UploadComponent })
      .then(uploadModal => {
        uploadModal.present();
        return uploadModal.onDidDismiss();
      })
      .then(resultData => {
        console.log(resultData.data, resultData.role);
      });
  }

  loadData(event) {
    setTimeout(() => {
      this.coolImages.push(this.tmpPepe);
      console.log("Done");
      event.target.complete();

      // App logic to determine if all data is loaded
      // and disable the infinite scroll
      let data = "asd";
      if (data.length == 1000) {
        event.target.disabled = true;
      }
    }, 500);
  }

  toggleInfiniteScroll() {
    this.infiniteScroll.disabled = !this.infiniteScroll.disabled;
  }

  doRefresh(event) {
    console.log("Begin async operation");

    setTimeout(() => {
      console.log("Async operation has ended");
      event.target.complete();
    }, 2000);
  }
}
