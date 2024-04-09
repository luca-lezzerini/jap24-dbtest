package al.polis.dbtest.service;

import al.polis.dbtest.dto.ProductDTO;
import al.polis.dbtest.model.Product;
import java.util.List;

public interface ProductManagerService {

    List<Product> getProductsList();

    List<Product> saveProduct(ProductDTO dto);

    List<Product> deleteProduct(Long id);

    List<Product> filterProducts(String filter);
}
