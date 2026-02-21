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
    private ProductService service;

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id){
        return service.findProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct){
        return service.createProduct(newProduct.getName(), newProduct.getPrice(), newProduct.getQuantity());
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product updateProduct ){
        return service.toUpdateProduct(id, updateProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        service.deleteProduct(id);
    }

}
