package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseVIewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseView2(Model model) {
        model.addAttribute("data", "Hello!!");
        return "response/hello";
    }

    // 권장하지 않는다. 완전 별로다.
    @RequestMapping("/response/hello")
    public void responseView3(Model model) {
        model.addAttribute("data", "Hello!!");
    }

}
