package io.authu.spring.jwt.core.request;

import org.springframework.util.PatternMatchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/29.
 */
public class JwtPathMatcher {

    private static List<String> excludePatterns = new ArrayList<>();

    public static boolean excludeMatch(String url) {
        return PatternMatchUtils.simpleMatch(excludePatterns.toArray(new String[0]), url);
    }

    public static void addExcludePatterns(List<String> patterns) {
        excludePatterns.addAll(patterns);
    }
}
