package org.ltejeda.springcloud.msvc.usuarios.service;

import org.ltejeda.springcloud.msvc.usuarios.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> list();

    Optional<User> byId(Long id);

    User save(User user);

    void delete(Long id);

    List <User> listByIds(Iterable<Long> ids);

    Optional<User> byEmail(String email);
    boolean existsByEmail(String email);
}
