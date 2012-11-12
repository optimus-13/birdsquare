package birdsquare.controller;

import birdsquare.model.Location;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "home/home";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home/home";
    }

    @RequestMapping(value = "/checkinlocations")
    public String checkin(@ModelAttribute("Location") Location location, Model model) {
        model.addAttribute("locationName", location.getName());
        model.addAttribute("latitude", location.getLatitude());
        model.addAttribute("longitude", location.getLongitude());
        return "checkin/checkinlocations";
    }

    @RequestMapping(value = "/profile")
    public String profile(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("checkinurl", "checkinlocations");
        return "profile/profile";
    }

}

