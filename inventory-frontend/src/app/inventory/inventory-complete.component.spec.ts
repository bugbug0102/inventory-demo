import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryCompleteComponent } from './inventory-complete.component';

describe('InventoryContentComponent', () => {
  let component: InventoryCompleteComponent;
  let fixture: ComponentFixture<InventoryCompleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryCompleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryCompleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
