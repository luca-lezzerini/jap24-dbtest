package al.polis.dbtest.controller;

import al.polis.dbtest.model.Product;
import al.polis.dbtest.service.ProductManagerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ProductManagementController {

    @Autowired
    private ProductManagerService productManagerService;

    @GetMapping("/getProductsList")
    @ResponseBody
    public List<Product> getProductsList() {
        return productManagerService.getProductsList();
    }
}
