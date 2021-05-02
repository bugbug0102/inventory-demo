import { Component, OnInit } from '@angular/core';
import { InventoryForEnquiryModel } from '../model/inventory-for-enquiry.model';
import { InventoryService } from '../service/inventory.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-inventory-content',
  templateUrl: './inventory-content.component.html',
  styleUrls: ['./inventory-content.component.scss']
})
export class InventoryContentComponent implements OnInit {

  constructor(private inventoryService:InventoryService, private route: ActivatedRoute) { }

  inventoryForEnquiries:InventoryForEnquiryModel[];

  ngOnInit(): void 
  {
    this.inventoryForEnquiries = this.route.snapshot.data['inventoryForEnquiries'];
  }


}
