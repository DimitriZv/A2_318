package detail.Service;

import detail.Model.ProductDetail;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface IDetailService {
    public abstract ResponseEntity<?> newDetail(ProductDetail detail);
    public abstract EntityModel<ProductDetail> one(Long id);
    public abstract ResponseEntity<?> replaceDetail(ProductDetail detail, Long id);
    public abstract ResponseEntity<?> deleteDetail(Long id);
    //public abstract Collection<ProductDetail> all();
    public abstract CollectionModel<EntityModel<ProductDetail>> all();
}
