import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
//https://jsonplaceholder.typicode.com/todos?_limit=2

export interface BoardSettingsInterface{
    id: number;
    title: string;
    show: boolean;
  }

@Injectable({providedIn: 'root'})
export class BoardSettingsService {
    public boardSettings: BoardSettingsInterface[] = [
        // {id: 1, select: false, title: 'param 1'},
        // {id: 2, select: true, title: 'param 2'}
      ];

    constructor(private http: HttpClient) {}

    fetchBoardSettings(): Observable<BoardSettingsInterface[]> {
      return this.http.get<BoardSettingsInterface[]>('http://localhost:8080/t-medicine-board/rest/settings')
        .pipe(tap(boardSettings => this.boardSettings = boardSettings))
    }

    onToggle(id: number){
        const index = this.boardSettings.findIndex(p => p.id === id);
        this.boardSettings[index].show = !this.boardSettings[index].show;
    }
}