import { Component, OnInit } from '@angular/core';
import { Ticket } from '../model/ticket';
import { ActivatedRoute, Router } from '@angular/router';
import { TicketService } from '../service/ticket.service';
import { SharedService } from '../shared/shared.service';
import { templateJitUrl } from '@angular/compiler';
declare var $: any;

@Component({
  selector: 'app-viewticket',
  templateUrl: './viewticket.component.html',
  styleUrls: ['./viewticket.component.css']
})
export class ViewticketComponent implements OnInit {

  openModal = false;

  userId: string;
  tickets: Array<Ticket>;
  page: number = 0;
  pageSize: number = 5;
  pages: Array<number>;

  constructor(private _route: ActivatedRoute,
    private _router: Router,
    private _userService : SharedService,
    private _ticketService: TicketService) { }

  ngOnInit(): void {
    this._userService.currentMessage.subscribe(message => this.userId = message);
    this.getTickets();
  }

  setPage(i: number, event: any) {
    event.preventDefault();
    this.page=i;
    this.getTickets();
  }

  ticketClick(ticket: Ticket){
    this._userService.changeTicketDetails(ticket);
    this._router.navigate(['/ticketdetails']);
  }

  getTickets(){
    this._ticketService.getUserTicketsFromRemote(this.userId, this.page, this.pageSize).subscribe(
      data => {
        console.log(data);
        this.tickets = data['content'];
        this.pages = new Array(data['totalPages']);
      },
      error => {
        this._router.navigate(['/login'])
      }
    )
 
  }
}
