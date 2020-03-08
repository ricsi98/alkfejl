package bme.aut.alkfejl.repository;

import bme.aut.alkfejl.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
