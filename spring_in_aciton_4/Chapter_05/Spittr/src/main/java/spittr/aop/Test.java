package spittr.aop;

import org.springframework.stereotype.Component;

@Component
public class Test {

    @DaoParamCheck(paramName = "userName", paramType = String.class)
    public void method(String userName) {
    }
}
