package br.com.cazzine.techstock.service;

import br.com.cazzine.techstock.exceptions.ProductNotFound;
import br.com.cazzine.techstock.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public Product createProduct(String name, BigDecimal price, int quantity){
         Integer newId = products.size() + 1;
         Product newProduct = new Product(newId, name, price, quantity);
         products.add(newProduct);
         return newProduct;
    }

    public List<Product> getAllProducts(){
        return products;
    }

    public Product findProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFound("product not found!"));
    }

    public Product toUpdateProduct(Integer id, Product updatedProduct){
          return products.stream()
                  .filter(product -> product.getId().equals(id))
                  .findFirst()
                  .map(product -> {product.setName(updatedProduct.getName());
                      product.setAmount(updatedProduct.getAmount());
                      product.setPrice(updatedProduct.getPrice());
                      return product;
                  })
                  .orElseThrow(() -> new ProductNotFound("product not found"));
    }

    public Boolean deleteProduct(Integer id){
        try{
            return products.removeIf(product -> product.getId().equals(id));
        } catch (ProductNotFound e){
            System.err.println("Error: " + e.getMessage());
        } return false;
    }
}
