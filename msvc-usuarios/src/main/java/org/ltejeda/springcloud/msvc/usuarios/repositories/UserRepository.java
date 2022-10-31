package org.ltejeda.springcloud.msvc.usuarios.repositories;

import org.ltejeda.springcloud.msvc.usuarios.models.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

}
