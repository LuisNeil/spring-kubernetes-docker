package org.ltejeda.springcloud.msvc.cursos.clients;

import org.ltejeda.springcloud.msvc.cursos.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users", url = "localhost:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    User detail(@PathVariable Long id);

    @PostMapping("/")
    User create(@RequestBody User user);

    @GetMapping("/users-by-course")
    List<User> getUsersByCourse(@RequestParam Iterable<Long> ids);


}
