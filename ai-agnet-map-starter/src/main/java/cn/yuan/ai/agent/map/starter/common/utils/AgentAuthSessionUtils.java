package cn.yuan.ai.agent.map.starter.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongcq
 * @since 2025/10/14
 */
public class AgentAuthSessionUtils {
    private static final ThreadLocal<Map<String, String>> contextHolder = new ThreadLocal();
    private static final String TOKEN = "token";

    public AgentAuthSessionUtils() {
    }

    public static void init() {
        contextHolder.set(new HashMap());
    }


    public static String getToken() {
        return (String) ((Map)contextHolder.get()).get(TOKEN);
    }

    public static void setToken(String token) {
        ((Map)contextHolder.get()).put(TOKEN, token);
    }


    public static void clear() {
        contextHolder.remove();
    }
}
