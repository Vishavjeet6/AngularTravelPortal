import { Injectable } from '@angular/core';
import { HttpClient, HttpBackend } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CoronaService {

  
  coronaUrl="https://wuhan-coronavirus-api.laeyoung.endpoint.ainize.ai/jhu-edu/timeseries?iso3=";
  restUrl = "&onlyCountries=true";

  constructor(private _http : HttpClient, handler: HttpBackend) {
    this._http = new HttpClient(handler);
   }

  public getCoronaFromRemote(countryCode: string):Observable<any>{
    console.log(this.coronaUrl+ countryCode +this.restUrl);
    return this._http.get(this.coronaUrl+ countryCode +this.restUrl);
  }

}


