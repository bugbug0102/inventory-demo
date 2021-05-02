import {Injectable} from '@angular/core'
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { InventoryForEnquiryModel } from '../model/inventory-for-enquiry.model';
import { CategoryForEnquiryModel } from '../model/category-for-enquiry.model';
import { SubCategoryForEnquiryModel } from '../model/sub-category-for-enquiry.model';
import { environment } from '../../environments/environment';

@Injectable()
export class InventoryService
{
    private readonly SERVER = environment.apiUrl;

    constructor(private httpClient: HttpClient)
    {

    }

    addInventory(form:{
        name:string
        , categoryName:string
        , subCategoryName:string
        , quantity:number
    }):Observable<Map<string,any>>
    {
        /*const headers = new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded'
        });

        const params = new HttpParams()
            .append('name', form.name)
            .append('categoryName', form.categoryName)
            .append('subCategoryName', form.subCategoryName)
            .append('quantity', <string><any>form.quantity);*/
        return this.httpClient.post<Map<string,any>>(`${this.SERVER}/inventory-web-api/inventory/add-inventory`,form);
    }

    updateInventory(form:{
        inventoryId:number
        , quantity:number
    }):Observable<Map<string,any>>
    {
        const headers = new HttpHeaders({
            'Content-Type': 'application/x-www-form-urlencoded'
        });

        const params = new HttpParams()
            .append('quantity', <string><any>form.quantity)
            .append('inventoryId', <string><any>form.inventoryId);
        return this.httpClient.post<Map<string,any>>(`${this.SERVER}/inventory-web-api/inventory/update-inventory`,form);
    }

    findInventoryForEnquiryAll():Observable<InventoryForEnquiryModel[]>
    {
        return this.httpClient.get<InventoryForEnquiryModel[]>(`${this.SERVER}/inventory-web-api/inventory/find-inventory-for-enquiry-all`);
    }

    getInventoryForEnquiryByInventoryId(inventoryId:number):Observable<InventoryForEnquiryModel>
    {
        const params = new HttpParams()
            .append('inventoryId', <string><any>inventoryId);
        return this.httpClient.get<InventoryForEnquiryModel>(`${this.SERVER}/inventory-web-api/inventory/get-inventory-for-enquiry-by-inventory-id`, { params: params});
    }



    findCategoryForEnquiryAll():Observable<CategoryForEnquiryModel[]>
    {
        return this.httpClient.get<CategoryForEnquiryModel[]>(`${this.SERVER}/inventory-web-api/inventory/find-category-for-enquiry-all`);
    }

    findSubCategoryForEnquiryAll():Observable<SubCategoryForEnquiryModel[]>
    {
        return this.httpClient.get<SubCategoryForEnquiryModel[]>(`${this.SERVER}/inventory-web-api/inventory/find-sub-category-for-enquiry-all`);
    }
  


}