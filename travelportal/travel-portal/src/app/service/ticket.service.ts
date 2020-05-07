import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ticket } from '../model/ticket';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private _http : HttpClient) { }

  public createTicketFromRemote(ticket: Ticket, userId: string):Observable<any>{
    return this._http.post<any>("http://localhost:8989/api/users/"+ userId +"/tickets", ticket);
  }

  public updateTicketFromRemote(ticket: Ticket, userId: string, ticketId: string):Observable<any>{
    return this._http.put<any>("http://localhost:8989/api/users/"+ userId +"/tickets/"+ ticketId, ticket);
  }

  public getUserTicketsFromRemote(userId: string, page: number, pageSize: number): Observable<any>{
    return this._http.get(`http://localhost:8989/api/users/${userId}/tickets?page=${page}&size=${pageSize}`);
  }

  public getUserTicketFromRemote(userId: string, ticketId: string): Observable<any>{
    return this._http.get(`http://localhost:8989/api/users/${userId}/tickets/${ticketId}`);
  }

  public getAllTicketsFromRemote(): Observable<any>{
    return this._http.get("http://localhost:8989/api/admin/tickets");
  }

  public getNotApprovedTicketsFromRemote(status: string): Observable<any>{
    return this._http.get(`http://localhost:8989/api/admin/tickets/${status}`);
  }
}
