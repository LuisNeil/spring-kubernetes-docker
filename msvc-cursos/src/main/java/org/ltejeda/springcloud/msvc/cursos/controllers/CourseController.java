package org.ltejeda.springcloud.msvc.cursos.controllers;

import feign.FeignException;
import org.ltejeda.springcloud.msvc.cursos.models.User;
import org.ltejeda.springcloud.msvc.cursos.models.entity.Course;
import org.ltejeda.springcloud.msvc.cursos.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<Course> o = service.byId(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validate(result);
        }
        Optional<Course> o = service.byId(id);
        if (o.isPresent()) {
            Course courseDb = o.get();
            courseDb.setName(course.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(courseDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Course> o = service.byId(id);
        if (o.isPresent()) {
            service.delete(o.get().getId());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/assign-user/{courseId}")
    public ResponseEntity<?> assignUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> o;
        try {
            o = service.assignUser(user, courseId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "The user does not exist  by " +
                            "id or error in communication: " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create-user/{courseId}")
    public ResponseEntity<?> createUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> o;
        try {
            o = service.createUser(user, courseId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "The user could not be created " +
                            "or error in communication: " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-user/{courseId}")
    public ResponseEntity<?> deleteUser(@RequestBody User user, @PathVariable Long courseId) {
        Optional<User> o;
        try {
            o = service.deleteUser(user, courseId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "The user does not exist  by " +
                            "id or error in communication: " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "The field " + e.getField() + " " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
