package universityproject.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/api/secure")
    public String secureTest(Principal principal) {
        return "Hello " + principal.getName();
    }
}
