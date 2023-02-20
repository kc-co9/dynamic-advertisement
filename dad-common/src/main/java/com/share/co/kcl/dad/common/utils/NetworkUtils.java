package com.share.co.kcl.dad.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class NetworkUtils {
    private NetworkUtils() {
    }

    public static Map<String, String> getHeader(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(16);
        Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, Object> getBody(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getParameterNames();

        Map<String, Object> parameterMap = new HashMap<>(16);
        while (enumeration.hasMoreElements()) {
            String parameter = enumeration.nextElement();
            String[] value = request.getParameterValues(parameter);
            parameterMap.put(parameter, value);
        }

        return parameterMap;
    }
}
