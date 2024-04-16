import { AssociateManProdDto } from './associate-man-prod-dto';
import { ManufacturerDto } from './manufacturer-dto';
import { IdDto } from './id-dto';
import { FilterDto } from './filter-dto';
import { ProductDto } from './dto/product-dto';
import { CalcReqDto } from './dto/calc-dto';
import { CalcResDto } from './dto/calc-dto';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  // product area
  code = "";
  description = "";
  price = 0.0;

  criterion = "";

  products: ProductDto[] = [];

  // manufacturer area
  name = "";
  selectedManufacturer: ManufacturerDto = new ManufacturerDto();

  mans: ManufacturerDto[] = [];

  constructor(private http: HttpClient) { }

  addProduct() {
    console.log("Adding...");
    let dto = new ProductDto();
    dto.code = this.code;
    dto.description = this.description;
    dto.price = this.price;
    let o: Observable<ProductDto[]> = this.http.post<ProductDto[]>("http://localhost:8080/insertProduct", dto);
    o.subscribe(resp => {
      this.products = resp;
      console.log("Returned value is ", this.products);
    });
  }

  refreshProducts() {
    let o: Observable<ProductDto[]> = this.http.get<ProductDto[]>("http://localhost:8080/getProductsList");
    o.subscribe(resp => {
      this.products = resp;
      console.log("Returned value is ", this.products);
    });
  }

  searchWithCrit() {
    let dto = new FilterDto();
    dto.filter = this.criterion;
    let o: Observable<ProductDto[]> = this.http.post<ProductDto[]>("http://localhost:8080/filterProducts", dto);
    o.subscribe(resp => {
      this.products = resp;
      console.log("Returned value is ", this.products);
    });
  }

  removeProduct(pr: ProductDto) {
    let dto = new IdDto();
    dto.id = pr.id;
    let o: Observable<ProductDto[]> = this.http.post<ProductDto[]>("http://localhost:8080/removeProduct", dto);
    o.subscribe(resp => {
      this.products = resp;
    });
  }

  addMan() {
    let dto = new ManufacturerDto();
    dto.manufacturerName = this.name;
    let o: Observable<ManufacturerDto[]> = this.http.post<ManufacturerDto[]>("http://localhost:8080/addManufacturer", dto);
    o.subscribe(resp => {
      this.mans = resp;
    });
  }

  refreshMans() {
    let o: Observable<ManufacturerDto[]> = this.http.get<ManufacturerDto[]>("http://localhost:8080/listManufacturers");
    o.subscribe(resp => {
      this.mans = resp;
    });
  }

  selectManufacturer(m: ManufacturerDto) {
    this.selectedManufacturer = m;
  }

  setSupplier(pr:ProductDto){
    let dto = new AssociateManProdDto();
    dto.manId = this.selectedManufacturer.id;
    dto.prodId = pr.id;
    let o: Observable<ProductDto[]> = this.http.post<ProductDto[]>("http://localhost:8080/assocProdMan", dto);
    o.subscribe(resp => {
      this.products = resp;
    });
  }

}
