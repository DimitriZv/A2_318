package detail.Controller;

import detail.Model.ProductDetail;
import detail.Repository.ProductDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductDetailRepository productDetailRepository) {

        return args -> {
            productDetailRepository.save(new ProductDetail("Snacks Description", "Some snack comment"));
            productDetailRepository.save(new ProductDetail("Chocolate Description", "Some choco comment"));
            productDetailRepository.save(new ProductDetail("Tim-Tam Description", "Tim-Tam comment"));

            productDetailRepository.findAll().forEach(detail -> log.info("Preloaded " + detail));
        };
    }
}