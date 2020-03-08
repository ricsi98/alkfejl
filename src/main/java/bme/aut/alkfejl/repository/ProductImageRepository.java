package bme.aut.alkfejl.repository;

import bme.aut.alkfejl.domain.Product;
import bme.aut.alkfejl.domain.ProductImage;
import org.springframework.data.repository.CrudRepository;

public interface ProductImageRepository extends CrudRepository<ProductImage, Integer> {
}
