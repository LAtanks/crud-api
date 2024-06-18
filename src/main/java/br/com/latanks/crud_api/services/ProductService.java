package br.com.latanks.crud_api.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.latanks.crud_api.services.exceptions.DataInvalidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.latanks.crud_api.models.ProductModel;
import br.com.latanks.crud_api.repositories.ProductRepository;
import br.com.latanks.crud_api.services.exceptions.NotFoundException;

@Service
public class ProductService {

    private static final String DEFAULT_IMAGE = "https://static.thenounproject.com/png/180604-200.png";

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<ProductModel> ProductsList() {
        var allProducts = this.productRepository.findAll();
        return allProducts;
    }

    @Transactional
    public ProductModel getProduct(Long id) {
        var product = this.findById(id);
        return product;
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

        if (obj.getName().isEmpty()) {
            throw new DataInvalidationException("Dado não insirido!");
        }
        if (obj.getPrice_in_cents() < 500)
            throw new DataInvalidationException("O preço tem que ser maior que R$5,00");

        if (Objects.isNull(obj.getImage_url())) {
            obj.setImage_url(DEFAULT_IMAGE);
        }
        obj.setActivate(true);
        return this.productRepository.save(obj);
    }

    @Transactional
    public void delete(Long id) {
        ProductModel obj = findById(id);
        obj.setActivate(false);
    }

    @Transactional
    ProductModel Desativate(Long id) {
        ProductModel obj = findById(id);
        obj.setActivate(true);
        return this.productRepository.save(obj);
    }

    private ProductModel findById(Long uuid) {
        Optional<ProductModel> product = this.productRepository.findById(uuid);

        return product.orElseThrow(() -> new NotFoundException("Identidade não encontrado id: " + uuid));
    }
}
