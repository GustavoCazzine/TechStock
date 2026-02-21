package br.com.cazzine.techstock.service;

import br.com.cazzine.techstock.Repository.ProductRepository;
import br.com.cazzine.techstock.exceptions.ProductNotFound;
import br.com.cazzine.techstock.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product createProduct(String name, BigDecimal price, int quantity){
         return repository.save(new Product(name, price, quantity));
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product findProductById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public Product toUpdateProduct(Integer id, Product updatedProduct){
         Product productUpdate = findProductById(id);

         productUpdate.setName(updatedProduct.getName());
         productUpdate.setQuantity(updatedProduct.getQuantity());
         productUpdate.setPrice(updatedProduct.getPrice());

         return repository.save(productUpdate);

    }

    public void deleteProduct(Integer id){
        repository.deleteById(id);
    }
}
