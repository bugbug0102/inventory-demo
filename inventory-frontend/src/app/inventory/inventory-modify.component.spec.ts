import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryModifyComponent } from './inventory-modify.component';

describe('InventoryModifyComponent', () => {
  let component: InventoryModifyComponent;
  let fixture: ComponentFixture<InventoryModifyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryModifyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InventoryModifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
