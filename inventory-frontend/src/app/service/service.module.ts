import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryService } from './inventory.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
    imports: [
      CommonModule,
      HttpClientModule,
    ],
    providers: [
      InventoryService,      
    ],
  })
  export class ServiceModule {}
  