package cn.yuan.ai.agent.map.starter.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongcq
 * @since 2025/12/5
 */
public class AgentProductSessionUtils {
    private static final ThreadLocal<Map<String, String>> contextHolder = new ThreadLocal();
    private static final String PRODUCT = "product";

    public AgentProductSessionUtils() {
    }

    public static void init() {
        contextHolder.set(new HashMap());
    }

    public static String getProduct() {
        return (String) ((Map)contextHolder.get()).get(PRODUCT);
    }

    public static void setProduct(String product) {
        ((Map)contextHolder.get()).put(PRODUCT, product);
    }


    public static void clear() {
        contextHolder.remove();
    }
}
