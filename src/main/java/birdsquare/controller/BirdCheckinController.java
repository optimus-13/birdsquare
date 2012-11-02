package birdsquare.controller;

import birdsquare.helper.BirdSquareSession;
import birdsquare.model.Checkin;

import birdsquare.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class BirdCheckinController {

    private BirdSquareSession birdSquareSession;

    @Autowired
    public BirdCheckinController(BirdSquareSession birdSquareSession) {
        this.birdSquareSession = birdSquareSession;
    }

    @RequestMapping(value = "/status")
    public String retrieveBirdNameFromUserAndRedirectToStatusPage(@ModelAttribute("checkin") Checkin checkin, Model model) {

        model.addAttribute("checkinurl", "birdcheckin");
        if (null != checkin && null != checkin.getBirdname()) {
            model.addAttribute("message", checkin.birdname + " check in success!");
            checkin.setDate(new Date());
            birdSquareSession.save(checkin);
        } else
            model.addAttribute("message", "Wrong input");

        return "checkin/status";
    }


    @RequestMapping(value = "/birdcheckin", method = RequestMethod.POST)
    public String birdcheckin( @ModelAttribute("Location") Location location, Model model){

        model.addAttribute("checkinurl", "status");
        model.addAttribute("locationname", location.getLocationname() );
        model.addAttribute("longitude", location.getLongitude());
        model.addAttribute("latitude", location.getLatitude());

        return "checkin/birdcheckin";
    }
}
