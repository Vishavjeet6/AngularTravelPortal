import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileuploadService {

  constructor(private _http : HttpClient) { }

  public addFileFromRemote(formData: FormData, ticketId: string):Observable<any>{
    return this._http.post<any>(`http://localhost:8989/api/uploadfile/${ticketId}`,formData);
  } 

  public getUserFilesFromRemote(ticketId: string): Observable<any>{
    return this._http.get(`http://localhost:8989/api/getfile/${ticketId}`);
  }
}

