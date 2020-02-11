package spittr.kantouSpringMVC;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DiguaHandlerAdapter implements HandlerAdapter, Ordered {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof DiguaController;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return ((DiguaController)handler).maidigua();
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1L;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
