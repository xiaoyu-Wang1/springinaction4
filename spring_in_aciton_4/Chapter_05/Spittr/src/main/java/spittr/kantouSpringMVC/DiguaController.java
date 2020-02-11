package spittr.kantouSpringMVC;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Component
public class DiguaController {

    public ModelAndView maidigua() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("maidigua");
        return mv;
    }


    public String shoudigua() {
        return "收地瓜";
    }
}
