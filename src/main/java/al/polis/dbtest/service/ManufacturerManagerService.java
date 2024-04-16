package al.polis.dbtest.service;

import al.polis.dbtest.dto.ManufacturerDto;
import java.util.List;

public interface ManufacturerManagerService {

    List<ManufacturerDto> getManufacturerList();

    List<ManufacturerDto> saveManufacturer(ManufacturerDto dto);

    List<ManufacturerDto> updateManufacturer(ManufacturerDto dto);

    List<ManufacturerDto> deleteManufacturer(Long id);

}
