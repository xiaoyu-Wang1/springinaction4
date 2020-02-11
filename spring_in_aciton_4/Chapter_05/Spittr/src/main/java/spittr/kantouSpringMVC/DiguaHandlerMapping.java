package spittr.kantouSpringMVC;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Component
public class DiguaHandlerMapping implements HandlerMapping, Ordered {

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {

        String uri = request.getRequestURI();
        String method = request.getMethod();

        if (uri.startsWith("/Gradle___spittr_war/tudou.do")) {
            if (method.equalsIgnoreCase("GET")) {
                return new HandlerExecutionChain(new DiguaController());
            } else if (method.equalsIgnoreCase("POST")) {
                return new HandlerExecutionChain(new DiguaController());
            } else {
                return new HandlerExecutionChain(new DiguaController());
            }
        }

        return null;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
