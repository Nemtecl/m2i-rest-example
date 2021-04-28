package fr.m2i.restexample.transformators;

import fr.m2i.restexample.dtos.ProductDto;
import fr.m2i.restexample.repositories.entities.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTransformator {
    public ProductDto modelToDto(final Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDto(product.getId(), product.getQuantity(), product.getTitle());
    }

    public List<ProductDto> modelsToDtos(final List<Product> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Product dtoToModel(final ProductDto dto) {
        if (dto == null) {
            return null;
        }
        return new Product(dto.getId(), dto.getQuantity(), dto.getTitle());
    }

    public List<Product> dtosToModels(final List<ProductDto> dtos) {
        return dtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
