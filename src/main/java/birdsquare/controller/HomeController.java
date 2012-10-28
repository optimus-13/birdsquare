package birdsquare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "home/home";
    }

    @RequestMapping(value = "/birdcheckin")
    public String checkin(){
        return "checkin/birdcheckin";
    }

    @RequestMapping(value = "/home")
    public String home(){
        return "home/home";
    }
}
