package org.ltejeda.springcloud.msvc.cursos.repositories;

import org.ltejeda.springcloud.msvc.cursos.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
