package br.com.geradordedevs.picpaysimplified.repositories;

import br.com.geradordedevs.picpaysimplified.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);

    UserEntity findByDocumentNumber(String documentNumber);
}
