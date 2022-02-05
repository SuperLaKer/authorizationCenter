package slktop.auth.resource.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/public")
    public String sayHi1(){
        return "hi1..";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String sayHi2(){
        return "hi2..";
    }

    @GetMapping("/normal")
    @PreAuthorize("hasRole('NORMAL')")
    public String sayHi3(){
        return "hi3..";
    }
}
