import { Component, OnInit } from '@angular/core';
import {BoardSettingsService} from "../shared/board-settings.service"
import {HttpErrorResponse} from "@angular/common/http"
import {delay} from 'rxjs/operators';

@Component({
  selector: 'app-board-settings',
  templateUrl: './board-settings.component.html',
  styleUrls: ['./board-settings.component.css']
})
export class BoardSettingsComponent implements OnInit {

  public loading: boolean = true;

  constructor(public boardSettingsService: BoardSettingsService) { }

  ngOnInit() {
    console.log ("ngOnInit");
    this.boardSettingsService.fetchBoardSettings()
    // .pipe(delay(2000))
      .subscribe(() => {
        this.loading = false
      },
      (err: HttpErrorResponse) => {
        console.log ("ERROR!!!");
        console.log (err.message);
      })
  }

  onSendSettings(){
    this.boardSettingsService.pushBoardSettings()
    .subscribe(() => {
      this.loading = false
    },
    (err: HttpErrorResponse) => {
      console.log ("ERROR!!!");
      console.log (err.message);
    })
  }

  onChange(id: number){
    this.boardSettingsService.onToggle(id);
  }

}
