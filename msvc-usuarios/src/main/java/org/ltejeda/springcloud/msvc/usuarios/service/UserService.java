package org.ltejeda.springcloud.msvc.usuarios.service;

import org.ltejeda.springcloud.msvc.usuarios.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> list();

    Optional<User> byId();

    User save(User user);

    void delete(Long id);
}
