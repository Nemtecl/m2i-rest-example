package fr.m2i.restexample.repositories;

import fr.m2i.restexample.repositories.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
