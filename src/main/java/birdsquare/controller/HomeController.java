package birdsquare.controller;

import birdsquare.helper.BirdSquareSession;
import birdsquare.helper.JsonReader;
import birdsquare.model.Bird;
import birdsquare.model.Location;
import birdsquare.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class HomeController {
    private BirdSquareSession birdSquareSession;

    @Autowired
    public HomeController(BirdSquareSession birdSquareSession) {
        this.birdSquareSession = birdSquareSession;
    }


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String index(Model model, @CookieValue(value= "fbuid", required = false, defaultValue = "") String uid) throws IOException, JSONException, ParseException {//, @CookieValue("fbusername") String username) {

        if (uid.equals("")){
            return "redirect:/login";
        }

        setProfile(model, uid);
        return "home/home";
    }

    private void setProfile(Model model, String uid) throws IOException, JSONException, ParseException {
        User user = (User) birdSquareSession.get(User.class, uid);
        if (user == null) {
            JSONObject userDetails = new JsonReader().readJsonFromUrl("http://graph.facebook.com/" + uid);
            String username = (String) userDetails.get("name");
            user = new User(uid, username);
            birdSquareSession.save(user);
        }

        model.addAttribute("points", user.getPoints());
        model.addAttribute("maxpoints",birdSquareSession.maximumPointsAmongUsers());
        model.addAttribute("temppoints",birdSquareSession.getPointsForLastSevenDays(uid));
        model.addAttribute("leaderboardlist", birdSquareSession.getSortedDescendingList(User.class, "points", 5));
    }

    @RequestMapping(value = "/checkinlocations")
    public String checkin(@ModelAttribute("Location") Location location, Model model) {
        model.addAttribute("locationName", location.getName());
        model.addAttribute("latitude", location.getLatitude());
        model.addAttribute("longitude", location.getLongitude());
        return "checkin/checkinlocations";
    }

    @RequestMapping(value = "/search")
    public String searchBirds(Model model){
        List<Bird> birdList = birdSquareSession.getAll(Bird.class);
        model.addAttribute("allbirds", birdList);
        return "search/search";
    }
}

