package br.com.latanks.crud_api.repositories;

import br.com.latanks.crud_api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
