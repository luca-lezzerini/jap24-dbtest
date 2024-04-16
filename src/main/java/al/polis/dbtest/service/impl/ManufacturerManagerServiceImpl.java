package al.polis.dbtest.service.impl;

import al.polis.dbtest.dto.ManufacturerDto;
import al.polis.dbtest.model.Manufacturer;
import al.polis.dbtest.repository.ManufacturerRepository;
import al.polis.dbtest.service.ManufacturerManagerService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManufacturerManagerServiceImpl implements ManufacturerManagerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<ManufacturerDto> getManufacturerList() {
        var mans = manufacturerRepository.findAll();
        List<ManufacturerDto> results = new ArrayList<>();
        if (mans != null) {
            mans.forEach(it -> {
                results.add(new ManufacturerDto(it));
            });
        }
        return results;
    }

    @Override
    public List<ManufacturerDto> saveManufacturer(ManufacturerDto dto) {
        var man = new Manufacturer(dto);
        System.out.println(man);
        manufacturerRepository.save(man);
        return getManufacturerList();
    }

    @Override
    public List<ManufacturerDto> updateManufacturer(ManufacturerDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ManufacturerDto> deleteManufacturer(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
