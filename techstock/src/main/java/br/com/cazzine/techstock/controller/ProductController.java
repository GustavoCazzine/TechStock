package br.com.cazzine.techstock.controller;

import br.com.cazzine.techstock.exceptions.ProductNotFound;
import br.com.cazzine.techstock.model.Product;
import br.com.cazzine.techstock.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        try {
            return service.findProductById(id);
        }catch (ProductNotFound e) {
            System.err.println("Error: " + e.getMessage());
        }   return null;
    }

    @PostMapping
    public Product creatProduct(@RequestBody Product informationsProduct){
        return service.createProduct(informationsProduct.getName(), informationsProduct.getPrice(), informationsProduct.getAmount());
    }

    @PutMapping("/{id}")
    public Product toUpdateProduct(@PathVariable Integer id, @RequestBody Product informationsToUpdate ){
        return service.toUpdateProduct(id, informationsToUpdate);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable Integer id){
        return service.deleteProduct(id);
    }

}
