package slktop.auth.resource.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/sayHi1")
    public String sayHi1(){
        return "hi1..";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sayHi2")
    public String sayHi2(){
        return "hi2..";
    }

    @GetMapping("/sayHi3")
    public String sayHi3(){
        return "hi3..";
    }

    @GetMapping("/sayHi4")
    public String sayHi4(){
        return "hi4..";
    }
}
