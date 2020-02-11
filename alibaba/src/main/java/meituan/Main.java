package meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
//    public static final String SPLITTER_ON = ";";
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String string = in.nextLine();
//            int index = in.nextInt();
//            String[] strings = string.split(SPLITTER_ON);
//            if (strings.length <= 0) {
//                System.out.println();
//            } else {
//                System.out.println(strings[index]);
//            }
//        }
//    }

    private static final String SEND_MAIL = "send_meail";

    public static void main(String[] args) {
        Student student = new Student();
        student.setUserName(null);

        Student student2 = new Student();
        student2.setUserName("xiaoyu");

        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        StudentChangeNameUtils.changeName(students);

        students.forEach(student1 -> System.out.println(student1.getUserName()));
    }
}

class Student {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

    // PCPassengersAddHandler，orderNo:null,msg:删除学生信息失败!
//    private Optional<ErrorMsgPatternMatchResult> match(String handlerName, String msg) {
//        if (StringUtils.isBlank(handlerName)) {
//            return Optional.empty();
//        }
//
//        if (StringUtils.isBlank(msg)) {
//            return Optional.empty();
//        }
//
//        // 处理 msg
//        msg = msg.replaceAll("\\s", "");
//
//        ErrorMsgPattern errorMsgPattern = this.conf.get(handlerName);
//        if (errorMsgPattern == null) {
//            return Optional.empty();
//        }
//
//        if (CollectionUtils.isEmpty(errorMsgPattern.getPatternBeanList())) {
//            return Optional.empty();
//        }
//
//
//        for (ErrorMsgPattern.PatternBean patternBean : errorMsgPattern.getPatternBeanList()) {
//            List<Pattern> patterns = patternBean.getPatterns();
//            for (Pattern pattern : patterns) {
//                Matcher msgMatcher = pattern.matcher(msg);
//                if (!msgMatcher.matches()) {
//                    continue;
//                }
//                return Optional.ofNullable(ErrorMsgPatternMatchResult.builder().code(patternBean.getCode()).errorMsg(msg).build());
//            }
//        }
//        return Optional.empty();
//    }