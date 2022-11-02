package org.ltejeda.springcloud.msvc.cursos.repositories;

import org.ltejeda.springcloud.msvc.cursos.models.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    @Modifying
    @Query("delete from CourseUser cu where cu.userId =?1")
    void deleteCourseUserById(Long id);
}
