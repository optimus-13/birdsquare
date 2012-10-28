package birdsquare.controller;

import birdsquare.helper.BirdSessionFactory;
import birdsquare.model.BirdInformation;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class BirdCheckinController implements Validator {

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public String retrieveBirdNameFromUserAndRedirectToStatusPage(@ModelAttribute("birdinformation") BirdInformation birdinformation,Model model) {

        if (null != birdinformation &&
                null != birdinformation.getBirdname()) {
            model.addAttribute("message", birdinformation.birdname+" check in success!");
            birdinformation.setDate(new Date());
            putObjectToTable(birdinformation);
        }
        else
            model.addAttribute("message", "Wrong input");
        return "checkin/status";
    }




    private void putObjectToTable(Object object)
    {

        final Session session = BirdSessionFactory.getInstance().createSession();
        session.beginTransaction();

        session.saveOrUpdate(object);

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"birdname","Field incorrect");
    }
}