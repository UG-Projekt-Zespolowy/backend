package universityproject.taskmanager.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/secure")
    public String secureTest(Principal principal) {
        return "Hello " + principal.getName();
    }
}
