package al.polis.dbtest.dto;

import al.polis.dbtest.model.Manufacturer;
import lombok.Data;

@Data
public class ManufacturerDto {

    private Long id;
    private String manufacturerName;

    public ManufacturerDto() {
    }

    public ManufacturerDto(Manufacturer man) {
        id = man.getId();
        manufacturerName = man.getManufacturerName();
    }

}
