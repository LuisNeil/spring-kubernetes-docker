package org.ltejeda.springcloud.msvc.cursos.services;


import org.ltejeda.springcloud.msvc.cursos.models.User;
import org.ltejeda.springcloud.msvc.cursos.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> list();

    Optional<Course> byId(Long id);

    Optional<Course> byIdWithUsers(Long id);

    Course save(Course course);

    void delete(Long id);

    Optional<User> assignUser(User user, Long courseId);

    Optional<User> createUser(User user, Long courseId);

    Optional<User> deleteUser(User user, Long courseId);

}
