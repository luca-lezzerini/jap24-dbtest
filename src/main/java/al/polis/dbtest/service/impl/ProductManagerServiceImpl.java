package al.polis.dbtest.service.impl;

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

}
