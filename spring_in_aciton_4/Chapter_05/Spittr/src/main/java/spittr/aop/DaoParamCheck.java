package spittr.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * 标注需要检测dao方法
 */
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DaoParamCheck {
    String paramName();
    Class paramType();
}
