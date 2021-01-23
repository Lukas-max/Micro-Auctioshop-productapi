package luke.auctioshopproductapi.bootdata;

import luke.auctioshopproductapi.product.model.Product;
import luke.auctioshopproductapi.product.service.ProductRepository;
import luke.auctioshopproductapi.productCategory.model.ProductCategory;
import luke.auctioshopproductapi.productCategory.service.ProductCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

//@Component
public class LoadProducts implements CommandLineRunner {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private static final String PATH = "static/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public LoadProducts(ProductCategoryRepository productCategoryRepository,
                        ProductRepository productRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ProductCategory categoryGames = new ProductCategory();
        categoryGames.setCategoryName("Gry");

        ProductCategory categoryElectronics = new ProductCategory();
        categoryElectronics.setCategoryName("Elektronika");

        productCategoryRepository.save(categoryElectronics);
        productCategoryRepository.save(categoryGames);
        logger.info("Załadowano do bazy podstawowe kategorie produktów.");

        Product product1 = new Product();
        product1.setSku("111");
        product1.setName("God of War 4");
        product1.setDescription("To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. ");
        product1.setUnitPrice(new BigDecimal("49.99"));
        product1.setActive(true);
        product1.setUnitsInStock(5);
        product1.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product1.setProductCategory(categoryGames);
        product1.setProductImage(getImage("gow2.jpg"));
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setSku("222");
        product2.setName("Final Fantasy VII Remake");
        product2.setDescription("To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry." +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry." +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry." +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry." +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. " +
                "To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. ");
        product2.setUnitPrice(new BigDecimal("199.99"));
        product2.setActive(true);
        product2.setUnitsInStock(30);
        product2.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product2.setProductCategory(categoryGames);
        product2.setProductImage(getImage("ff7r.jpg"));
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setSku("333");
        product3.setName("Ghost Of Tsushima");
        product3.setDescription("To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. ");
        product3.setUnitPrice(new BigDecimal("249.99"));
        product3.setActive(true);
        product3.setUnitsInStock(50);
        product3.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product3.setProductCategory(categoryGames);
        product3.setProductImage(getImage("Tsusima.jpg"));
        productRepository.save(product3);

        Product product4 = new Product();
        product4.setSku("444");
        product4.setName("Sony Playstation 4");
        product4.setDescription("Konsola do gier");
        product4.setUnitPrice(new BigDecimal("1999"));
        product4.setActive(true);
        product4.setUnitsInStock(10);
        product4.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product4.setProductCategory(categoryElectronics);
        product4.setProductImage(getImage( "ps4.jpg"));
        productRepository.save(product4);

        Product product5 = new Product();
        product5.setSku("555");
        product5.setName("The Last Of Us");
        product5.setDescription("To jest test opisu gry. To jest test opisu gry. To jest test opisu gry. ");
        product5.setUnitPrice(new BigDecimal("49.99"));
        product5.setActive(true);
        product5.setUnitsInStock(9);
        product5.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product5.setProductCategory(categoryGames);
        product5.setProductImage(getImage( "the-last-of-us.jpg"));
        productRepository.save(product5);

        Product product6 = new Product();
        product6.setSku("323");
        product6.setName("X-box");
        product6.setDescription("To jest test opisu gry. To jest est opisu gry. X-box");
        product6.setUnitPrice(new BigDecimal("1349.99"));
        product6.setActive(false);
        product6.setUnitsInStock(0);
        product6.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product6.setProductCategory(categoryElectronics);
        product6.setProductImage(getImage( "xbox.jpg"));
        productRepository.save(product6);

        Product product7 = new Product();
        product7.setSku("888");
        product7.setName("Horizon Zero Dawn");
        product7.setDescription("To jest test opisu gry Horizon Zero Dawn.");
        product7.setUnitPrice(new BigDecimal("49.99"));
        product7.setActive(true);
        product7.setUnitsInStock(54);
        product7.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product7.setProductCategory(categoryGames);
        product7.setProductImage(getImage( "horizon.jpeg"));
        productRepository.save(product7);

        Product product8 = new Product();
        product8.setSku("999");
        product8.setName("Fallout 2");
        product8.setDescription("To jest test opisu gry Fallout 2.");
        product8.setUnitPrice(new BigDecimal("29.99"));
        product8.setActive(true);
        product8.setUnitsInStock(94);
        product8.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product8.setProductCategory(categoryGames);
        product8.setProductImage(getImage( "fallout2.jpg"));
        productRepository.save(product8);

        Product product9 = new Product();
        product9.setSku("101010");
        product9.setName("Mortal Kombat 11");
        product9.setDescription("To jest test opisu gry Mortal Kombat 11.");
        product9.setUnitPrice(new BigDecimal("99.99"));
        product9.setActive(true);
        product9.setUnitsInStock(323);
        product9.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product9.setProductCategory(categoryGames);
        product9.setProductImage(getImage( "mk11.jpg"));
        productRepository.save(product9);

        Product product10 = new Product();
        product10.setSku("12121212");
        product10.setName("Detroit: Become Human");
        product10.setDescription("To jest test opisu gry Detroit: Become Human.");
        product10.setUnitPrice(new BigDecimal("74.99"));
        product10.setActive(true);
        product10.setUnitsInStock(23);
        product10.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product10.setProductCategory(categoryGames);
        product10.setProductImage(getImage( "detroit.jpg"));
        productRepository.save(product10);

        Product product11 = new Product();
        product11.setSku("13131313");
        product11.setName("Wiedźmin 3: Dziki Gon");
        product11.setDescription("To jest test opisu gry Wiedźmin 3: Dziki Gon.");
        product11.setUnitPrice(new BigDecimal("144.99"));
        product11.setActive(true);
        product11.setUnitsInStock(15);
        product11.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product11.setProductCategory(categoryGames);
        product11.setProductImage(getImage( "witcher.jpg"));
        productRepository.save(product11);

        Product product12 = new Product();
        product12.setSku("141414141");
        product12.setName("Skully");
        product12.setDescription("To jest test opisu gry Skully.");
        product12.setUnitPrice(new BigDecimal("144.99"));
        product12.setActive(true);
        product12.setUnitsInStock(155);
        product12.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product12.setProductCategory(categoryGames);
        product12.setProductImage(getImage( "skully.jpg"));
        productRepository.save(product12);

        Product product13 = new Product();
        product13.setSku("151515151");
        product13.setName("The Last Of Us 2");
        product13.setDescription("To jest test opisu gry The Last Of Us 2.");
        product13.setUnitPrice(new BigDecimal("244.99"));
        product13.setActive(true);
        product13.setUnitsInStock(1550);
        product13.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product13.setProductCategory(categoryGames);
        product13.setProductImage(getImage( "last2.jpg"));
        productRepository.save(product13);

        Product product14 = new Product();
        product14.setSku("161616161");
        product14.setName("Resident Evil 3");
        product14.setDescription("To jest test opisu gry Resident Evil 3.");
        product14.setUnitPrice(new BigDecimal("99.99"));
        product14.setActive(true);
        product14.setUnitsInStock(123);
        product14.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product14.setProductCategory(categoryGames);
        product14.setProductImage(getImage( "evil3.jpg"));
        productRepository.save(product14);

        Product product15 = new Product();
        product15.setSku("171717171");
        product15.setName("Half Life Alyx");
        product15.setDescription("To jest test opisu gry Half Life Alyx.");
        product15.setUnitPrice(new BigDecimal("69.99"));
        product15.setActive(true);
        product15.setUnitsInStock(223);
        product15.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product15.setProductCategory(categoryGames);
        product15.setProductImage(getImage( "alyx.jpg"));
        productRepository.save(product15);

        Product product16 = new Product();
        product16.setSku("18181818");
        product16.setName("Microsoft Flight Simulator");
        product16.setDescription("To jest test opisu gry Microsoft Flight Simulator.");
        product16.setUnitPrice(new BigDecimal("229.99"));
        product16.setActive(true);
        product16.setUnitsInStock(22);
        product16.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product16.setProductCategory(categoryGames);
        product16.setProductImage(getImage( "simulator.jpg"));
        productRepository.save(product16);

        Product product17 = new Product();
        product17.setSku("19191919");
        product17.setName("Project Cars Game 2");
        product17.setDescription("To jest test opisu gry Project Cars 2.");
        product17.setUnitPrice(new BigDecimal("139.99"));
        product17.setActive(true);
        product17.setUnitsInStock(13);
        product17.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product17.setProductCategory(categoryGames);
        product17.setProductImage(getImage( "cars2.jpg"));
        productRepository.save(product17);

        Product product18 = new Product();
        product18.setSku("2020202");
        product18.setName("Windbound");
        product18.setDescription("To jest test opisu gry Windbound.");
        product18.setUnitPrice(new BigDecimal("229.99"));
        product18.setActive(true);
        product18.setUnitsInStock(131);
        product18.setDateTimeCreated(new Timestamp(System.currentTimeMillis()));
        product18.setProductCategory(categoryGames);
        product18.setProductImage(getImage( "bound.jpg"));
        productRepository.save(product18);
        logger.info("Załadowano do bazy wstępne produkty. Gratuluję.");
    }

    private byte[] getImage(String path) throws IOException {
        Resource resource = new ClassPathResource(PATH + path);
        return resource.getInputStream().readAllBytes();
    }
}
