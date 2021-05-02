import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { InventoryForEnquiryModel } from '../model/inventory-for-enquiry.model';
import { CategoryForEnquiryModel } from '../model/category-for-enquiry.model';
import { SubCategoryForEnquiryModel } from '../model/sub-category-for-enquiry.model';
import { InventoryService } from '../service/inventory.service';

@Injectable()
export class ResolveInventoryForEnquiryAll implements Resolve<InventoryForEnquiryModel[]>
{
  constructor(private inventoryService: InventoryService) {}
  resolve(route: ActivatedRouteSnapshot)
  {
    return this.inventoryService.findInventoryForEnquiryAll();
  }
}

@Injectable()
export class ResolveCategoryForEnquiryAll implements Resolve<CategoryForEnquiryModel[]>
{
  constructor(private inventoryService: InventoryService) {}
  resolve(route: ActivatedRouteSnapshot)
  {
    return this.inventoryService.findCategoryForEnquiryAll();
  }
}

@Injectable()
export class ResolveSubCategoryForEnquiryAll implements Resolve<SubCategoryForEnquiryModel[]>
{
  constructor(private inventoryService: InventoryService) {}
  resolve(route: ActivatedRouteSnapshot)
  {
    return this.inventoryService.findSubCategoryForEnquiryAll();
  }
}

@Injectable()
export class ResolveInventoryForEnquiryByInvetoryId implements Resolve<InventoryForEnquiryModel>
{
  constructor(private inventoryService: InventoryService) {}
  resolve(route: ActivatedRouteSnapshot)
  {
    const inventoryId = route.params['inventoryId'];
    return this.inventoryService.getInventoryForEnquiryByInventoryId(inventoryId);
  }
}
