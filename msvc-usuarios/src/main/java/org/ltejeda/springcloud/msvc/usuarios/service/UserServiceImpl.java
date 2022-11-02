package org.ltejeda.springcloud.msvc.usuarios.service;

import org.ltejeda.springcloud.msvc.usuarios.models.entity.User;
import org.ltejeda.springcloud.msvc.usuarios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> list() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> byId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> listByIds(Iterable<Long> ids) {
        return (List<User>) repository.findAllById(ids);
    }

    @Override
    public Optional<User> byEmail(String email) {
        return repository.byEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }


}
