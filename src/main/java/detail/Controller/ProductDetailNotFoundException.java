package detail.Controller;

public class ProductDetailNotFoundException extends RuntimeException {

    public ProductDetailNotFoundException(Long id) {
        super("Could not find product details " + id);
    }
}