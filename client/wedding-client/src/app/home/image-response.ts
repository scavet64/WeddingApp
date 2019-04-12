export class ImageResponse {
  imageUrl: String;
  personUploaded: String;
  comment: String;
  title: String;

  constructor(
    imageUrl: String,
    personUploaded: String,
    comment: String,
    title: String
  ) {
    this.imageUrl = imageUrl;
    this.personUploaded = personUploaded;
    this.comment = comment;
    this.title = title;
  }
}
