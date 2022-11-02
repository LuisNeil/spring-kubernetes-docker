package org.ltejeda.springcloud.msvc.cursos.services;

import org.ltejeda.springcloud.msvc.cursos.clients.UserClientRest;
import org.ltejeda.springcloud.msvc.cursos.models.User;
import org.ltejeda.springcloud.msvc.cursos.models.entity.Course;
import org.ltejeda.springcloud.msvc.cursos.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository repository;

    @Autowired
    
    private UserClientRest clientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Course> list() {
        return (List<Course>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> byId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> assignUser(User user, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> createUser(User user, Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(User user, Long courseId) {
        return Optional.empty();
    }
}
