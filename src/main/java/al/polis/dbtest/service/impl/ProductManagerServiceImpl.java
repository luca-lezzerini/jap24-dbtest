package al.polis.dbtest.service.impl;

import al.polis.dbtest.dto.ProductDTO;
import al.polis.dbtest.model.Product;
import al.polis.dbtest.repository.ProductRepository;
import al.polis.dbtest.service.ProductManagerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerServiceImpl implements ProductManagerService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductsList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> saveProduct(ProductDTO dto) {
        var p = new Product(dto);
        System.out.println("Before saving ... " + p);
        p = productRepository.save(p);
        System.out.println("After saving ... " + p);
        return getProductsList();
    }

    @Override
    public List<Product> filterProducts(String filter) {
        System.out.println("In service filter is " + filter);
        var list = productRepository.findByDescriptionLike(filter);
        return list;
    }

    @Override
    public List<Product> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

}
