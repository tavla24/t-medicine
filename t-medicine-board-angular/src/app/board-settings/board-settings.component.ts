import { Component, OnInit } from '@angular/core';
import {BoardSettingsService} from "../shared/board-settings.service"
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
    this.boardSettingsService.fetchBoardSettings()
    // .pipe(delay(2000))
      .subscribe(() => {
        this.loading = false
      })
  }

  onChange(id: number){
    this.boardSettingsService.onToggle(id);
  }

}
