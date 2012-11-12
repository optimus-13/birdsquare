package birdsquare.controller;

import birdsquare.helper.BirdSquareSession;
import birdsquare.model.Checkin;
import birdsquare.model.Location;
import birdsquare.model.User;
import org.hibernate.SQLQuery;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BirdCheckinController {

    private BirdSquareSession birdSquareSession;
    private SQLQuery sqlQuery;

    @Autowired
    public BirdCheckinController(BirdSquareSession birdSquareSession) {
        this.birdSquareSession = birdSquareSession;
    }

    @RequestMapping(value = "/profilesuccess", method = RequestMethod.POST)
    public String retrieveBirdNameFromUserAndRedirectToProfilePage(@ModelAttribute("checkin") Checkin checkin, Model model, @RequestParam("birdName") String birdName) {

        String sql = "select id from bird where scientific_name='" + birdName + "';";
        sqlQuery = birdSquareSession.createSQLQuery(sql);
        if(sqlQuery.list().isEmpty())
        {
            System.out.println("Is empty");
            throw new ResourceNotFoundException("No bird name found");

        }
        checkin.setBirdId((Integer) sqlQuery.list().get(0));

        User user = (User) birdSquareSession.get(User.class,checkin.getFbuid());
        if (null != checkin && null != birdName) {

            birdSquareSession.save(checkin);
            if( user == null)
            {
                user = new User(checkin.getFbuid());
            }
            user.incrementPointsByOne();
            birdSquareSession.saveOrUpdate(user);
        }
        model.addAttribute("points", user.getPoints());

        return "profile/profile";
    }


    @RequestMapping(value = "/checkinform", method = RequestMethod.POST)
    public String birdcheckin(@ModelAttribute("Location") Location location, Model model) throws JSONException {
        model.addAttribute("locationName", location.getName());
        model.addAttribute("longitude", location.getLongitude());
        model.addAttribute("latitude", location.getLatitude());
        Checkin checkin = new Checkin();
        List<String> birdNameList = checkin.getBirdNameList(birdSquareSession);
        model.addAttribute("allbirds", birdNameList);

        return "checkin/checkinform";
    }

}
