package br.com.latanks.crud_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.latanks.crud_api.models.ProductModel;
import br.com.latanks.crud_api.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<ProductModel> ProductsList() {
        var allProducts = this.productRepository.findAll();
        return allProducts;
    }

    @Transactional
    public ProductModel update(ProductModel obj) {
        var newObj = findById(obj.getId());
        newObj.setName(obj.getName());
        newObj.setPrice_in_cents(obj.getPrice_in_cents());
        return this.productRepository.save(newObj);
    }

    @Transactional
    public ProductModel register(ProductModel obj) {
        obj.setId(null);
        obj.setActivate(true);
        return this.productRepository.save(obj);
    }

    @Transactional
    public void delete(Long id) {
        ProductModel obj = findById(id);
        obj.setActivate(false);
    }

    @Transactional ProductModel Desativate(Long id){
        ProductModel obj = findById(id);
        obj.setActivate(true);
        return this.productRepository.save(obj);
    }

    private ProductModel findById(Long uuid) {
        Optional<ProductModel> product = this.productRepository.findById(uuid);

        return product.orElseThrow(() -> new RuntimeException("Produto não encontrado, Id: " + uuid));
    }
}
