package meituan;

import java.util.List;

/**
 * @author wangyuquan
 * @date 2019-11-20 14:59
 */
public class StudentChangeNameUtils {

    public static void changeName(List<Student> students) {
        students.forEach(student -> student.setUserName("hello"));
    }
}
