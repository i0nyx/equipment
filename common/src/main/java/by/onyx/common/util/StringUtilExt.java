package by.onyx.common.util;

import org.springframework.util.StringUtils;

public class StringUtilExt extends StringUtils {

    public static boolean isNoBlank(String s) {
        return !StringUtils.isEmpty(s);
    }


}
