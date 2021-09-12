package detail.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import detail.Controller.ProductDetailController;
import detail.Controller.ProductDetailModelAssembler;
import detail.Model.ProductDetail;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import detail.Repository.ProductDetailRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import detail.Controller.ProductDetailModelAssembler;
import detail.Controller.ProductDetailNotFoundException;
import detail.Model.ProductDetail;
import detail.Repository.ProductDetailRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductDetailService implements IDetailService {

    private final ProductDetailRepository repository;

    private final ProductDetailModelAssembler assembler;

    public ProductDetailService(ProductDetailRepository repository, ProductDetailModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @Override
    public ResponseEntity<?> newDetail(ProductDetail newProductDetail) {

        EntityModel<ProductDetail> entityModel = assembler.toModel(repository.save(newProductDetail));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @Override
    public EntityModel<ProductDetail> one(Long id) {

        ProductDetail productDetail = repository.findById(id) //
                .orElseThrow(() -> new ProductDetailNotFoundException(id));

        return assembler.toModel(productDetail);
    }

    @Override
    public ResponseEntity<?> replaceDetail(ProductDetail newProductDetail, Long id) {

        ProductDetail updatedProductDetail = repository.findById(id) //
                .map(detail -> {
                    detail.setDescription(newProductDetail.getDescription());
                    detail.setComment(newProductDetail.getComment());
                    return repository.save(detail);
                }) //
                .orElseGet(() -> {
                    newProductDetail.setId(id);
                    return repository.save(newProductDetail);
                });

        EntityModel<ProductDetail> entityModel = assembler.toModel(updatedProductDetail);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @Override
    public ResponseEntity<?> deleteDetail(Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    //Collection<ProductDetail> all() {
    public CollectionModel<EntityModel<ProductDetail>> all() {

        List<EntityModel<ProductDetail>> details = repository.findAll().stream() //
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(details, linkTo(methodOn(ProductDetailController.class).all()).withSelfRel());
    }
}
