package al.polis.dbtest.model;

import al.polis.dbtest.dto.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false)
    private String code;
    @Column(length = 1024, nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(length = 1024)
    private String notes;
    
    @ManyToOne
    private Manufacturer manufacturer;

    public Product(ProductDTO dto) {
        id = dto.getId();
        code = dto.getCode();
        description = dto.getDescription();
        price = dto.getPrice();
        notes = dto.getNotes();
    }

}
