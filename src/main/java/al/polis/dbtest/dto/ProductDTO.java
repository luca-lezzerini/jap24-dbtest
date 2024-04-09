package al.polis.dbtest.dto;

import al.polis.dbtest.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String code;
    private String description;
    private Double price;
    private String notes;

    public ProductDTO(Product p) {
        id = p.getId();
        code = p.getCode();
        description = p.getDescription();
        price = p.getPrice();
        notes = p.getNotes();
    }

}
