import {Component, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from '../model/ticket';
import { TicketService } from '../service/ticket.service';

import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SharedService } from '../shared/shared.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {

  displayedColumns: string[] = ['id', 'priority', 'requestType', 'updatedAt', 'projectName', 'travelCity', 'status', 'user.businessUnit'];
  dataSource: MatTableDataSource<Ticket>;
  flag = true;
  msg: string = "See Aprooved Ticket Too..";

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
 
  tickets: Array<Ticket>;
  userIds: Array<number> = [];

  constructor(private _router: Router, 
    private _ticketService: TicketService,
    private _sharedService : SharedService,
    private _authService: AuthService) { }

  ngOnInit(): void {
    this.getNotApprovedTickets();
  }

  signOut(){
    this._authService.logoutAdmin();
    this._router.navigate(['/']);
  }

  openTicket(ticket: Ticket){
    this._sharedService.changeTicketDetails(ticket);
    this._router.navigate(['/adminticket']);
  }

  triggerGetTickets(){
    if(this.flag){
      this.getAllTickets();
      this.msg = "Hide Approved Tickets!";
    }else{
      this.getNotApprovedTickets();
      this.msg = "See Aprooved Ticket Too..";
    }
    this.flag = !this.flag;
  }


  getNotApprovedTickets(){
    this._ticketService.getNotApprovedTicketsFromRemote("APPROVED").subscribe(
      data => {
        console.log(data);
        this.tickets = data;

    },
      error => {
        console.log(error)
        console.log("exception occured");
        this._router.navigate(['/adminlogin'])
      },
      ()=>{ 

        this.dataSource = new MatTableDataSource(this.tickets);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sortingDataAccessor = (item:Ticket, property) => {
          switch(property) {
            case 'user.businessUnit': return item.user.businessUnit;
            case 'priority': {
              if(item.priority == 'Immediate') return 1;
              if(item.priority == 'Urgent') return 0;
              if(item.priority == 'Normal') return -1;
            }
            default: return item[property];
          }
        };
        this.dataSource.sort = this.sort;

        this.dataSource.filterPredicate = (data, filter: string)  => {
          const accumulator = (currentTerm, key) => {
            return key === 'user' ? currentTerm + data.user.businessUnit : currentTerm + data[key];
          };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };
      }
    )
  }


  getAllTickets(){
    this._ticketService.getAllTicketsFromRemote().subscribe(
      data => {
        console.log(data);
        this.tickets = data;

    },
      error => {
        console.log(error)
        console.log("exception occured");
        this._router.navigate(['/adminlogin'])
      },
      ()=>{ 

        this.dataSource = new MatTableDataSource(this.tickets);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sortingDataAccessor = (item:Ticket, property) => {
          switch(property) {
            case 'user.businessUnit': return item.user.businessUnit;
            case 'priority': {
              if(item.priority == 'Immediate') return 1;
              if(item.priority == 'Urgent') return 0;
              if(item.priority == 'Normal') return -1;
            }
            default: return item[property];
          }
        };
        this.dataSource.sort = this.sort;

        this.dataSource.filterPredicate = (data, filter: string)  => {
          const accumulator = (currentTerm, key) => {
            return key === 'user' ? currentTerm + data.user.businessUnit : currentTerm + data[key];
          };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };
      }
    )
  }
  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
