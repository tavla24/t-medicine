import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
//https://jsonplaceholder.typicode.com/todos?_limit=2

export interface BoardSettingsInterface{
    id: number;
    title: string;
    show: boolean;
  }

@Injectable({providedIn: 'root'})
export class BoardSettingsService {
    public boardSettings: BoardSettingsInterface[] = [
        {id: 1, show: false, title: 'param 1'},
        {id: 2, show: false, title: 'param 2'}
      ];
      
    private httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Cache-Control': 'no-cache',
          'Access-Control-Allow-Credentials' : 'true',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, PATCH, DELETE, PUT, OPTIONS',
          'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
        })
      } 

    private url = 'http://localhost:8080/t-medicine-board/rest/settings';

    constructor(private http: HttpClient) {}

    fetchBoardSettings(): Observable<BoardSettingsInterface[]> {
      console.log ("fetchBoardSettings");
      return this.http.get<BoardSettingsInterface[]>(this.url)
        .pipe(tap(boardSettings => this.boardSettings = boardSettings))
      // return this.pushBoardSettings();
    }

    pushBoardSettings(): Observable<BoardSettingsInterface[]>  {
      console.log ("pushBoardSettings");
      return this.http.put<BoardSettingsInterface[]>(this.url, JSON.stringify(this.boardSettings), this.httpOptions)
        .pipe(retry(1), catchError(this.handleError))
        .pipe(tap(boardSettings => this.boardSettings = boardSettings))
    }

    postBoardSettings() {
      console.log ("postBoardSettings");
      return this.http.post(this.url, JSON.stringify(this.boardSettings), this.httpOptions);
      // .toPromise().then(res => res.json() as Customer).catch(this.handleError);
    }

    onToggle(id: number){
      console.log ("onToggle");
        const index = this.boardSettings.findIndex(p => p.id === id);
        this.boardSettings[index].show = !this.boardSettings[index].show;
    }

      // Error handling 
  handleError(error) {
    console.log ("handleError");
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
 }
}