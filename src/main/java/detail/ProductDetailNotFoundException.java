package detail;

class ProductDetailNotFoundException extends RuntimeException {

    ProductDetailNotFoundException(Long id) {
        super("Could not find product details " + id);
    }
}