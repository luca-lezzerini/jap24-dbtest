package al.polis.dbtest.controller;

import al.polis.dbtest.dto.ManufacturerDto;
import al.polis.dbtest.service.ManufacturerManagerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Slf4j
public class ManufacturerManagementController {

    @Autowired
    private ManufacturerManagerService manufacturerManagerService;

    @PostMapping("addManufacturer")
    @ResponseBody
    public List<ManufacturerDto> addManufacturer(@RequestBody ManufacturerDto dto) {
        System.out.println(dto);
        return manufacturerManagerService.saveManufacturer(dto);
    }

    @GetMapping("listManufacturers")
    @ResponseBody
    public List<ManufacturerDto> listManufacturers() {
        return manufacturerManagerService.getManufacturerList();
    }
}
