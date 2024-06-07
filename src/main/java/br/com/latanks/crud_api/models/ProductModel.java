package br.com.latanks.crud_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Product")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ProductModel {

  public ProductModel(Long id, String name, Integer price_in_cents, String image_url) {
    this.id = id;
    this.name = name;
    this.price_in_cents = price_in_cents;
    this.image_url = image_url;
    this.activate = true;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, updatable = false, nullable = false)
  private Long id;

  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull
  @Column(name = "price_in_cents", nullable = false)
  private Integer price_in_cents;

  @Column(name = "imagem_url", nullable = true)
  private String image_url;

  @Column(name = "activate", nullable = false, updatable = true)
  private Boolean activate;

}
