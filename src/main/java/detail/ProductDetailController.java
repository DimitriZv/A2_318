package detail;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class ProductDetailController {

    private final ProductDetailRepository repository;

    private final ProductDetailModelAssembler assembler;

    ProductDetailController(ProductDetailRepository repository, ProductDetailModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/details")
    CollectionModel<EntityModel<ProductDetail>> all() {

        List<EntityModel<ProductDetail>> details = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(details, linkTo(methodOn(ProductDetailController.class).all()).withSelfRel());
    }

    @PostMapping("/details")
    ResponseEntity<?> newDetail(@RequestBody ProductDetail newProductDetail) {

        EntityModel<ProductDetail> entityModel = assembler.toModel(repository.save(newProductDetail));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @GetMapping("/details/{id}")
    EntityModel<ProductDetail> one(@PathVariable Long id) {

        ProductDetail productDetail = repository.findById(id) //
                .orElseThrow(() -> new ProductDetailNotFoundException(id));

        return assembler.toModel(productDetail);
    }

    @PutMapping("/details/{id}")
    ResponseEntity<?> replaceDetail(@RequestBody ProductDetail newProductDetail, @PathVariable Long id) {

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

    @DeleteMapping("/details/{id}")
    ResponseEntity<?> deleteDetail(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}