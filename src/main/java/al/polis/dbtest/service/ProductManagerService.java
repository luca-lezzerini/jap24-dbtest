package al.polis.dbtest.service;

import al.polis.dbtest.dto.ProductDTO;
import java.util.List;

public interface ProductManagerService {

    List<ProductDTO> getProductsList();

    List<ProductDTO> saveProduct(ProductDTO dto);

    List<ProductDTO> updateProduct(ProductDTO dto);

    List<ProductDTO> deleteProduct(Long id);

    List<ProductDTO> filterProducts(String filter);

    List<ProductDTO> assocManProd(Long idMan, Long idProd);
}
