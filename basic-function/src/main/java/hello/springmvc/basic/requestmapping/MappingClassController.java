package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class MappingClassController {

    //@GetMapping("/users")
    @GetMapping
    public String user() {
        return "get users";
    }

    //@PostMapping("/users")
    @PostMapping
    public String addUser() {
        return "post user";
    }

    //@GetMapping("/users/{userId}")
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId) {
        return "get userId=" + userId;
    }

    //@PatchMapping("/users/{userId}")
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update userId=" + userId;
    }

    //@DeleteMapping("/users/{userId}")
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete userId=" + userId;
    }

}
