package br.com.cazzine.techstock.controller;

import br.com.cazzine.techstock.dto.ProductRequestDTO;
import br.com.cazzine.techstock.dto.ProductResponseDTO;
import br.com.cazzine.techstock.exceptions.ProductNotFound;
import br.com.cazzine.techstock.model.Product;
import br.com.cazzine.techstock.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductResponseDTO> getAllProducts(){
        List<Product> listaDeEntidades =  service.getAllProducts();
        return listaDeEntidades.stream()
                .map(produtoEntidade -> new ProductResponseDTO(
                        produtoEntidade.getId(),
                        produtoEntidade.getName(),
                        produtoEntidade.getPrice(),
                        produtoEntidade.getQuantity()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Integer id){
        Product productEntity = service.findProductById(id);

        ProductResponseDTO response = new ProductResponseDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getQuantity()
        );

        return response;
    }

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody @Valid ProductRequestDTO newProductDTO){
        // 1. Manda o Service salvar e guarda a Entidade retornada
        Product productEntity = service.createProduct(newProductDTO.getName(), newProductDTO.getPrice(), newProductDTO.getQuantity());

        // 2. Converte para DTO antes de devolver para o cliente
        return new ProductResponseDTO(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getQuantity()
        );
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductRequestDTO updateProduct ){
        Product productEntity = service.toUpdateProduct(id, updateProduct);

        ProductResponseDTO response = new ProductResponseDTO(
                id,
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getQuantity()
        );
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id){
        service.deleteProduct(id);
    }

}
