import { Component,ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-tabset',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './tabset.component.html',
  styles: [`
    :host nb-tab {
      padding: 1.25rem;
    }
  `],
})
export class TabsetComponent {

}
