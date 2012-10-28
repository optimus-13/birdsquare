package birdsquare.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class HomeControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private AnnotationMethodHandlerAdapter handlerAdapter;
    private HomeController controller;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
        controller = new HomeController();
    }

    @Test
    public void shouldRenderIndexAsLandingPage() throws Exception {
        request.setRequestURI("/");
        request.setMethod("GET");

        final ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertViewName(mav, "home/home");
    }

    @Test
    public void shouldRenderCheckInPageAfterClickingCheckInButton() throws Exception {
        request.setRequestURI("/birdcheckin");

        final ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertViewName(mav, "checkin/birdcheckin");
    }

    @Test
    public void shouldRenderHomePageAfterClickingHomeButton() throws Exception {
        request.setRequestURI("/home");

        final ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertViewName(mav, "home/home");
    }
}