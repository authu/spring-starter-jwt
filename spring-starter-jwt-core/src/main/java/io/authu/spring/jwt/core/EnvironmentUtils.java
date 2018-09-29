package io.authu.spring.jwt.core;

import org.springframework.core.env.Environment;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/9/29.
 */
public class EnvironmentUtils {

    public static String getApplicationName(Environment environment) {
        String applicationName = environment.getProperty("spring.application.name");
        if (applicationName != null) {
            return applicationName.toLowerCase();
        }
        return null;
    }

}
