package al.polis.dbtest.controller;

import al.polis.dbtest.dto.FilterDTO;
import al.polis.dbtest.dto.IdDTO;
import al.polis.dbtest.dto.ProductDTO;
import al.polis.dbtest.service.ProductManagerService;
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
        List<ProductDTO> list = productManagerService.getProductsList();
        return list;
    }

    @PostMapping("/insertProduct")
    @ResponseBody
    public List<ProductDTO> insertProduct(@RequestBody ProductDTO dto) {
        System.out.println("insertProduct " + dto);
        var list = productManagerService.saveProduct(dto);
        return list;
    }

    @PostMapping("/updateProduct")
    @ResponseBody
    public List<ProductDTO> updateProduct(@RequestBody ProductDTO dto) {
        System.out.println("updateProduct " + dto);
        var list = productManagerService.updateProduct(dto);
        return list;
    }
    
    @PostMapping("/removeProduct")
    @ResponseBody
    public List<ProductDTO> removeProduct(@RequestBody IdDTO dto) {
        System.out.println("removeProduct " + dto);
        var list = productManagerService.deleteProduct(dto.getId());
        return list;
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
        return list;
    }
}
