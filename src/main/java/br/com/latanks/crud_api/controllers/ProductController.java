package br.com.latanks.crud_api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.latanks.crud_api.models.ProductModel;
import br.com.latanks.crud_api.services.ProductService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/product")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductModel>> ProductsList() {
        var allProducts = this.productService.ProductsList();
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable("id") Long id) {
        var product = this.productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody @Valid ProductModel data) {
        data.setId(null);
        data.setActivate(true);
        this.productService.register(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid ProductModel obj, @PathVariable("id") Long id) {
        obj.setId(id);
        this.productService.update(obj);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
