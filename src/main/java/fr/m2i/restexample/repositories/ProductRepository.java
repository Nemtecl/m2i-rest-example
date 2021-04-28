package fr.m2i.restexample.repositories;

import fr.m2i.restexample.repositories.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
