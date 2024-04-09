package al.polis.dbtest.controller;

import al.polis.dbtest.dto.FilterDTO;
import al.polis.dbtest.dto.ProductDTO;
import al.polis.dbtest.model.Product;
import al.polis.dbtest.service.ProductManagerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ProductManagementController {

    @Autowired
    private ProductManagerService productManagerService;

    @GetMapping("/getProductsList")
    @ResponseBody
    public List<ProductDTO> getProductsList() {
        List<Product> list = productManagerService.getProductsList();
        List<ProductDTO> dto = new ArrayList<>();
        list.forEach(p -> dto.add(new ProductDTO(p)));
        return dto;
    }

    @PostMapping("/insertProduct")
    @ResponseBody
    public List<ProductDTO> insertProduct(@RequestBody ProductDTO dto) {
        System.out.println("insertProduct " + dto);
        var list = productManagerService.saveProduct(dto);
        List<ProductDTO> dtos = new ArrayList<>();
        list.forEach(p -> dtos.add(new ProductDTO(p)));
        return dtos;
    }

    @PostMapping("/filterProducts")
    @ResponseBody
    public List<ProductDTO> filterProducts(@RequestBody FilterDTO dto) {
        System.out.println("filterProducts " + dto);
        String filter = Optional.ofNullable(dto.getFilter())
                .map(s -> "%" + s + "%")
                .orElse("%");
        System.out.println("Filter is " + filter);
        var list = productManagerService.filterProducts(filter);
        List<ProductDTO> dtos = new ArrayList<>();
        list.forEach(p -> dtos.add(new ProductDTO(p)));
        return dtos;
    }
}
