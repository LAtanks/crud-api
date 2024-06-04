package br.com.latanks.crud_api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.latanks.crud_api.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

}