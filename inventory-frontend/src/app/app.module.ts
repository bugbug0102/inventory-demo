import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InventoryContentComponent } from './inventory/inventory-content.component';
import { InventoryModifyComponent } from './inventory/inventory-modify.component';
import { InventoryCompleteComponent } from './inventory/inventory-complete.component';
import { HomeContentComponent } from './home/home-content.component';
import { ServiceModule } from './service/service.module';
import { ResolveInventoryForEnquiryAll
  , ResolveCategoryForEnquiryAll
  , ResolveSubCategoryForEnquiryAll
  , ResolveInventoryForEnquiryByInvetoryId
} from './inventory/inventory.resolve';


@NgModule({
  declarations: [
    AppComponent,
    InventoryContentComponent,
    InventoryModifyComponent,
    InventoryCompleteComponent,
    HomeContentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ServiceModule,
  ],
  providers: [
    ResolveInventoryForEnquiryAll
    , ResolveCategoryForEnquiryAll
    , ResolveSubCategoryForEnquiryAll
    ,ResolveInventoryForEnquiryByInvetoryId
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
