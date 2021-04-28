package fr.m2i.restexample.controllers;

import fr.m2i.restexample.dtos.ProductDto;
import fr.m2i.restexample.dtos.ProductXmlDto;
import fr.m2i.restexample.dtos.ProductsXmlDto;
import fr.m2i.restexample.exceptions.M2I404Exception;
import fr.m2i.restexample.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto getById(@PathVariable Long id) throws M2I404Exception {
        return productService.findById(id);
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto productDto) throws M2I404Exception {
        return productService.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws M2I404Exception {
        productService.delete(id);
    }
}
