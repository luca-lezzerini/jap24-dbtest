/*
 */
package al.polis.dbtest;

import al.polis.dbtest.dto.ManufacturerDto;
import al.polis.dbtest.dto.ProductDTO;
import al.polis.dbtest.repository.ManufacturerRepository;
import al.polis.dbtest.repository.ProductRepository;
import al.polis.dbtest.service.ManufacturerManagerService;
import al.polis.dbtest.service.ProductManagerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManufacturesUnitTest {

    private static final String TESTCODE1 = "Testcode1";

    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerManagerService manufacturerManagerService;
    @Autowired
    private ProductManagerService productManagerService;

    public ManufacturesUnitTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    @Order(1)
    public void testInitialDeletion() {
        emptyTables();
    }

    @Test
    @Order(2)
    public void testManufacturers() {
        var list = manufacturerManagerService.getManufacturerList();
        Assertions.assertTrue(list.isEmpty());

        ManufacturerDto m1 = new ManufacturerDto();
        m1.setManufacturerName("Test manufacturer 1");
        manufacturerManagerService.saveManufacturer(m1);
        list = manufacturerManagerService.getManufacturerList();
        Assertions.assertTrue(list.size() == 1);

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> manufacturerManagerService.updateManufacturer(m1));
    }

    @Test
    @Order(3)
    public void testProducts() {
        var list = productManagerService.getProductsList();
        Assertions.assertTrue(list.isEmpty());

        var dto = new ProductDTO();
        dto.setCode(TESTCODE1);
        dto.setDescription("Test description1");
        dto.setNotes("");
        dto.setPrice(123.45);
        productManagerService.saveProduct(dto);
        list = productManagerService.getProductsList();
        Assertions.assertTrue(list.size() == 1);
        var prod = list.get(0);
        Assertions.assertTrue(prod.getCode().equals(TESTCODE1));

    }

    @Test
    @Order(4)
    public void testRelationships() {
        // retrieve all the products and manufacturers
        var mans = manufacturerRepository.findAll();
        var prods = productRepository.findAll();

        // take the first one of each
        var man = mans.get(0);
        var prod = prods.get(0);

        // Associate them
        man.getProducts().add(prod);
        manufacturerRepository.save(man);
        prod.setManufacturer(man);
        productRepository.save(prod);
        
        // read the product
        var prod2 = productRepository
                .findById(prod.getId())
                .orElseThrow(() -> new RuntimeException());
        // retrieve its manufacturer
        var man2 = prod2.getManufacturer();
        // check everything is ok
        Assertions.assertEquals(man.getId(), man2.getId());
    }

    private void emptyTables() {
        productRepository.deleteAllInBatch();
        manufacturerRepository.deleteAllInBatch();
    }
}
