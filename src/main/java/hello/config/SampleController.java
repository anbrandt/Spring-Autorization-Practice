package hello.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public String sample(Model model) {
        model.addAttribute("message", "This message is vissible only for ROLE_ADMIN users");
        return "sample";
    }


}
