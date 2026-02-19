package br.com.cazzine.techstock.service;

import br.com.cazzine.techstock.exceptions.ProductNotFound;
import br.com.cazzine.techstock.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private List<Product> products = new ArrayList<>();

    public Product cadastrarProduto(String name, BigDecimal price, int quantity){
         Integer newId = products.size() + 1;
         return new Product(newId, name, price, quantity);
    }

    public List<Product> listProduct(){
        return products;
    }

    public Product searchProductForId(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ProductNotFound("product not found!"));
    }
}
