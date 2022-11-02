package org.ltejeda.springcloud.msvc.cursos.services;

import org.ltejeda.springcloud.msvc.cursos.clients.UserClientRest;
import org.ltejeda.springcloud.msvc.cursos.models.User;
import org.ltejeda.springcloud.msvc.cursos.models.entity.Course;
import org.ltejeda.springcloud.msvc.cursos.models.entity.CourseUser;
import org.ltejeda.springcloud.msvc.cursos.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public Optional<Course> byIdWithUsers(Long id) {
        Optional<Course> o = repository.findById(id);
        if(o.isPresent()){
            Course course = o.get();
            if(!course.getCourseUsers().isEmpty()){
                List<Long> ids = course.getCourseUsers().stream()
                        .map(CourseUser::getUserId).toList();
                List<User> users =clientRest.getUsersByCourse(ids);
                course.setUsers(users);
            }
            return Optional.of(course);
        }
        return Optional.empty();
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
    @Transactional
    public Optional<User> assignUser(User user, Long courseId) {
        Optional<Course> o = repository.findById(courseId);
        if(o.isPresent()){

            User userMsvc = clientRest.detail(user.getId());

            Course course = o.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());

            course.addCourseUser(courseUser);
            repository.save(course);
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId) {

        Optional<Course> o = repository.findById(courseId);
        if(o.isPresent()){

            User newUserMsvc = clientRest.create(user);

            Course course = o.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(newUserMsvc.getId());

            course.addCourseUser(courseUser);
            repository.save(course);
            return Optional.of(newUserMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> deleteUser(User user, Long courseId) {
        Optional<Course> o = repository.findById(courseId);
        if(o.isPresent()){

            User userMsvc = clientRest.detail(user.getId());

            Course course = o.get();
            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());

            course.removeCourseUser(courseUser);
            repository.save(course);
            return Optional.of(userMsvc);
        }
        return Optional.empty();
    }
}
