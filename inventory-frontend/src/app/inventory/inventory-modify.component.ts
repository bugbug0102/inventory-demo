import { Component, OnInit } from '@angular/core';
import { CategoryForEnquiryModel } from '../model/category-for-enquiry.model';
import { SubCategoryForEnquiryModel } from '../model/sub-category-for-enquiry.model';
import { InventoryService } from '../service/inventory.service';
import { ActivatedRoute, Router } from '@angular/router';
import { InventoryForEnquiryModel } from '../model/inventory-for-enquiry.model';

@Component({
  selector: 'app-inventory-modify',
  templateUrl: './inventory-modify.component.html',
  styleUrls: ['./inventory-modify.component.scss']
})
export class InventoryModifyComponent implements OnInit 
{
  mode = 'add';

  inventoryId:number;
  name:string;
  categoryName:string;
  subCategoryName:string;
  quantity:number;
  error:string;

  categoryForEnquiries:CategoryForEnquiryModel[];
  subCategoryForEnquiries:SubCategoryForEnquiryModel[];


  constructor(private inventoryService:InventoryService, private route: ActivatedRoute, private router:Router) { }

  ngOnInit(): void 
  {
    this.categoryForEnquiries = this.route.snapshot.data['categoryForEnquiries'];
    this.subCategoryForEnquiries = this.route.snapshot.data['subCategoryForEnquiries'];

    
    if(this.route.snapshot.data['inventoryForEnquiry']!=null)
    {
      const ife = <InventoryForEnquiryModel> this.route.snapshot.data['inventoryForEnquiry'];
      this.inventoryId = ife.inventoryId;
      this.name = ife.name;
      this.categoryName = ife.categoryName;
      this.subCategoryName = ife.subCategoryName;
      this.quantity = ife.quantity;
      this.mode = 'update';
    }

  }


  onSubmit():void
  {
    this.error = null;
    if(this.name!=null && this.categoryName!=null && this.subCategoryName && this.quantity > 0)
    {
      if(this.mode == 'add')
      {
        this.inventoryService.addInventory({
          name: this.name
          , categoryName: this.categoryName
          , subCategoryName: this.subCategoryName
          , quantity: this.quantity
        }).subscribe( res => {
          
          if(res['error']!=null)
          {
            this.error = res['error'][0].code;

          }else
          {
            this.router.navigate(['/inventory/complete']);
          }

        });
      }else
      {
        this.inventoryService.updateInventory({
          inventoryId: this.inventoryId
          , quantity: this.quantity
        }).subscribe( res => {

          this.router.navigate(['/inventory/complete']);
        });

      }
      

    }else
    {
      this.error = 'invalid.input';
    }
    
  }

}
