import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeContentComponent } from './home/home-content.component';
import { InventoryContentComponent } from './inventory/inventory-content.component';
import { InventoryModifyComponent } from './inventory/inventory-modify.component';
import { InventoryCompleteComponent } from './inventory/inventory-complete.component';
import { ResolveInventoryForEnquiryAll, ResolveCategoryForEnquiryAll, ResolveSubCategoryForEnquiryAll, ResolveInventoryForEnquiryByInvetoryId } from './inventory/inventory.resolve';

const routes: Routes = [
  {
    path: '', component: HomeContentComponent
  }
  ,{ 
    path: 'inventory'
    ,children: [
      { 
        path: ''
        , component: InventoryContentComponent
        , resolve: {
            inventoryForEnquiries: ResolveInventoryForEnquiryAll
        }
      }
      ,{ 
          path: 'add'
          , component: InventoryModifyComponent
          , resolve: {
            categoryForEnquiries: ResolveCategoryForEnquiryAll
            ,subCategoryForEnquiries: ResolveSubCategoryForEnquiryAll
          }
      }
      ,{ 
          path: 'modify/:inventoryId'
          , component: InventoryModifyComponent
          , resolve: 
          {
            categoryForEnquiries: ResolveCategoryForEnquiryAll
            ,subCategoryForEnquiries: ResolveSubCategoryForEnquiryAll
            , inventoryForEnquiry: ResolveInventoryForEnquiryByInvetoryId
          }
      }
      ,{ 
        path: 'complete'
        , component: InventoryCompleteComponent
    }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
