package aa.auth2.authServer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class DemoController {

	@GetMapping("/normal")
	public String sayHiNormal(){
	    return DemoController.class.getSimpleName()+"...NORMAL...";
	}

	@GetMapping("/admin")
	public String sayHiAdmin(){
		return DemoController.class.getSimpleName()+"...ADMIN...";
	}
}
