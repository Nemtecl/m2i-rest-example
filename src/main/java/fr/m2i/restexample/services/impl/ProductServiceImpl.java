package fr.m2i.restexample.services.impl;

import fr.m2i.restexample.dtos.ProductDto;
import fr.m2i.restexample.exceptions.M2I404Exception;
import fr.m2i.restexample.repositories.ProductRepository;
import fr.m2i.restexample.repositories.entities.Product;
import fr.m2i.restexample.services.ProductService;
import fr.m2i.restexample.transformators.ProductTransformator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductTransformator productTransformator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductTransformator productTransformator) {
        this.productRepository = productRepository;
        this.productTransformator = productTransformator;
    }


    @Override
    public List<ProductDto> findAll() {
        return productTransformator.modelsToDtos((List<Product>) productRepository.findAll());
    }

    @Override
    public ProductDto findById(Long id) throws M2I404Exception {
        Optional<Product> byId = productRepository.findById(id);
        return productTransformator.modelToDto(byId.orElseThrow(() -> new M2I404Exception("Le produit "
                + id + " n'existe pas")));
    }

    @Override
    public ProductDto create(ProductDto dto) {
        Product product = new Product();
        product.setQuantity(dto.getQuantity());
        product.setTitle(dto.getTitle());

        return productTransformator.modelToDto(productRepository.save(product));
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) throws M2I404Exception {
        Product p = productRepository.findById(id).orElseThrow(() -> new M2I404Exception("Le produit "
                + id + " n'existe pas"));
        p.setTitle(p.getTitle());
        p.setQuantity(p.getQuantity());
        return productTransformator.modelToDto(productRepository.save(p));
    }

    @Override
    public void delete(Long id) throws M2I404Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new M2I404Exception("Le produit "
                + id + " n'existe pas"));
        productRepository.delete(product);
    }
}
