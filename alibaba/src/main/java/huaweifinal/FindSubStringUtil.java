package huaweifinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 串联所有单词的子串
 *
 * @author wangyuquan
 * @date 2019-12-01 14:43
 */
public class FindSubStringUtil {

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"}));
    }

    private static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> integerList = new ArrayList<>();
        if (isBlank(s) || words.length == 0) {
            return integerList;
        }

        if (s.length() < words.length * words[1].length()) {
            return integerList;
        }

        HashMap<String, Integer> wordsMap = new HashMap<>(words.length);
        for (String word : words) {
            wordsMap.merge(word, 1, Integer::sum);
        }

        int arrayLen = words.length;
        int wordLen = words[1].length();
        // 扫描字符串
        for (int i = 0; i < s.length() - arrayLen * wordLen + 1; i++) {
            String subStr = s.substring(i, i + wordLen * arrayLen);
            HashMap<String, Integer> newWordsMap = new HashMap<>(wordsMap.size());

            boolean flag = true;
            for (int j = 0; j < words.length; j++) {
                String wordStr = subStr.substring(j * wordLen, (j + 1) * wordLen);
                if (wordsMap.get(wordStr) == null) {
                    flag = false;
                    break;
                } else {
                    newWordsMap.merge(wordStr, 1, Integer::sum);
                    if (newWordsMap.get(wordStr) > wordsMap.get(wordStr)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                integerList.add(i);
            }
        }

        return integerList;
    }

    private static boolean isBlank(final String s) {
        return s == null || s.length() == 0;
    }
}
