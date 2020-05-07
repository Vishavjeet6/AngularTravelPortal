import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from '../shared/shared.service';
import { Ticket } from '../model/ticket';
import {NgForm} from '@angular/forms';
import { TicketService } from '../service/ticket.service';
import { FileuploadService } from '../service/fileupload.service';
import { saveAs } from 'file-saver';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-adminticket',
  templateUrl: './adminticket.component.html',
  styleUrls: ['./adminticket.component.css']
})


export class AdminticketComponent implements OnInit {

  ticket: Ticket;
  expenseBourneBy: string;
  isReadOnly: boolean = true;
  isAdmin: boolean = true;
  updateDate: string;
  msg = '';
  dropdownChanged: boolean = false;
  filesToUpload: Array<File> = [];
  uploadedFiles: Array<File>= [];

  constructor(private _router: Router,
    private _sharedService: SharedService,
    private _service: TicketService,
    private _fileService: FileuploadService,
    private _authService: AuthService) { }

  ngOnInit(): void {
    this._sharedService.currentTicket.subscribe(message => this.ticket = message);
    this.ticket.status = "INPROCESS";
    this._fileService.getUserFilesFromRemote(this.ticket.id.toString()).subscribe(
      data =>{
        console.log(data);
        this.uploadedFiles = data; 
        console.log("File Fetched Successfully");
      },
      error => {
        console.log("File Cannot be Fetched");
        console.log(error);
      });
    this.updateDate = this.formatDate(this.ticket.updatedAt);
    this._service.updateTicketFromRemote(this.ticket, this.ticket.user.id.toString(), this.ticket.id.toString()).subscribe(
      data =>{
        console.log(data);
        console.log("status changed in db");

      },
      error => {
        console.log("exception occured");
        console.log(error);
        this.msg = "Cannot change status in db"
        alert(this.msg);
        this._router.navigate(['/adminticket']);
      } 
    )    
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>>fileInput.target.files;
}

  onOptionsSelected(){
    this.dropdownChanged = true;
  }

  signOut(){
    this._authService.logoutAdmin();
    this._router.navigate(['/']);
  }

  formatDate(date: string): string{
    let a = date.split('.');
    let d = a[0].split('T');
    return d[0] + ", " + d[1];
  }

  editForm(){
    this.isReadOnly= !this.isReadOnly;
  }

  downloadFile(fileDownload: any){
    const byteString = atob(fileDownload.fileByte);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
     int8Array[i] = byteString.charCodeAt(i);
   }
    const file = new Blob([int8Array], {type: fileDownload.type});
    saveAs(file, fileDownload.name);

  }

  updateTicket(){
    if(!this.dropdownChanged){
      alert("Kindly Change Status");
      return;
    }
    if(this.ticket.id == null){
      this.msg = "Cannot update ticket"
      alert(this.msg);
      this._router.navigate(['/adminhome']);
    }
      this._service.updateTicketFromRemote(this.ticket, this.ticket.user.id.toString(), this.ticket.id.toString()).subscribe(
        data =>{
          console.log(data);
          console.log("Ticket Successfully Updated");
        },
        error => {
          console.log("exception occured");
          console.log(error);
          this.msg = "Cannot update ticket"
          alert(this.msg);
          this._router.navigate(['/adminticket']);
          return;
        },
        ()=>{
          const formData: any = new FormData();
          const files: Array<File> = this.filesToUpload;
          console.log(files);
          for(let i =0; i < files.length; i++){
            formData.append("file",files[i]);
          }
          this._fileService.addFileFromRemote(formData, this.ticket.id.toString()).subscribe(
            data =>{
              console.log(data);
              console.log("File Successfully Uploaded");
              this.msg = "Ticket Successfully Updated";
              alert(this.msg);
              this._router.navigate(['/adminhome']);
            },
            error => {
              console.log("File Cannot be uploaded");
              console.log(error);
              this.msg = "File Cannot be uploaded"
              alert(this.msg);
              this._router.navigate(['/adminticket']);
            }
          )
        }
      )    
  }

}
