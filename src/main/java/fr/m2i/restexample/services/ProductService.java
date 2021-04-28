package fr.m2i.restexample.services;

import fr.m2i.restexample.dtos.ProductDto;
import fr.m2i.restexample.exceptions.M2I404Exception;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findById(Long id) throws M2I404Exception;

    ProductDto create(ProductDto dto);

    ProductDto update(Long id, ProductDto dto) throws M2I404Exception;

    void delete(Long id) throws M2I404Exception;
}
