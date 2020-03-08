package bme.aut.alkfejl.controllers;

import bme.aut.alkfejl.domain.Product;
import bme.aut.alkfejl.domain.ProductImage;
import bme.aut.alkfejl.repository.ProductImageRepository;
import bme.aut.alkfejl.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewProduct (@RequestParam String name, @RequestParam String description) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        productRepository.save(p);
        return "Saved";
    }

    @PostMapping(path = "/add_image/{product_id}")
    public @ResponseBody ProductImage addNewImage (@PathVariable (value = "product_id") Integer product_id, @Valid @RequestBody ProductImage pi) {
        return productRepository.findById(product_id).map(product -> {
            pi.setProduct(product);
            return productImageRepository.save(pi);
        }).orElseThrow(() -> new ResourceNotFoundException("product_id " + product_id + " not found!"));
    }

    @GetMapping(path = "/all_images")
    public @ResponseBody Iterable<ProductImage> getAllImagesToProduct(@RequestParam Integer product_id) {
        Product p = productRepository.findById(product_id).orElseThrow(() -> new EntityNotFoundException());
        return p.getImages();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
