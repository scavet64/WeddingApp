import { Component, OnInit } from '@angular/core';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss'],
})
export class UploadComponent implements OnInit {

  constructor(private modalController: ModalController) { }

  ngOnInit() {}

  onUpload() {
    // Do stuff
    this.modalController.dismiss({comment: 'comment dummy'}, 'confirm');
  }

  onCancel() {
    this.modalController.dismiss(null, 'cancel');
  }

}
