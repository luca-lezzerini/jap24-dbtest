package al.polis.dbtest.service.impl;

import al.polis.dbtest.dto.ProductDTO;
import al.polis.dbtest.model.Manufacturer;
import al.polis.dbtest.model.Product;
import al.polis.dbtest.repository.ManufacturerRepository;
import al.polis.dbtest.repository.ProductRepository;
import al.polis.dbtest.service.ProductManagerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductManagerServiceImpl implements ProductManagerService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<ProductDTO> getProductsList() {
        return toListOfProductDto(productRepository.findAll());
    }

    @Override
    public List<ProductDTO> saveProduct(ProductDTO dto) {
        var p = new Product(dto);
        System.out.println("Before saving ... " + p);
        p = productRepository.save(p);
        System.out.println("After saving ... " + p);
        return getProductsList();
    }

    @Override
    public List<ProductDTO> updateProduct(ProductDTO dto) {
        Product p = productRepository.findById(dto.getId())
                .map(it -> it)
                .orElseThrow(() -> {
                    return new RuntimeException();
                });
        System.out.println("Before saving ... " + p);
        p.setCode(dto.getCode());
        p.setDescription(dto.getDescription());
        p.setNotes(dto.getNotes());
        p.setPrice(dto.getPrice());
        p = productRepository.save(p);
        System.out.println("After saving ... " + p);
        return getProductsList();
    }

    @Override
    public List<ProductDTO> filterProducts(String filter) {
        System.out.println("In service filter is " + filter);
        var list = toListOfProductDto(
                productRepository.findByDescriptionLike(filter, Sort.by(Sort.Direction.ASC, "description")));
        return list;
    }

    @Override
    public List<ProductDTO> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return toListOfProductDto(productRepository.findAll());
    }

    @Override
    public List<ProductDTO> assocManProd(Long idMan, Long idProd) {
        Product pr = productRepository.findById(idProd)
                .orElseThrow(() -> new RuntimeException());
        Manufacturer ma = manufacturerRepository.findById(idMan)
                .orElseThrow(() -> new RuntimeException());
        pr.setManufacturer(ma);
        productRepository.save(pr);
        
        return getProductsList();
    }

    private List<ProductDTO> toListOfProductDto(List<Product> list) {
        List<ProductDTO> dtos = new ArrayList<>();
        list.forEach(p -> dtos.add(new ProductDTO(p)));
        return dtos;
    }

}
