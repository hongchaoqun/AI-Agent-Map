package cn.yuan.agent.map.ab.local.utils;

import com.baidubce.appbuilder.base.component.Component;
import com.baidubce.appbuilder.base.utils.http.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpEntity;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hongcq
 * @since 2025/12/4
 */
@Slf4j
public class V3HttpClient extends HttpClient {
    private static final Logger LOGGER = Logger.getLogger(Component.class.getName());

    public V3HttpClient(String secretKey, String gateway, String gatewayV2) {
        super(secretKey, gateway, gatewayV2);
    }

    /**
     * V3 文档上写了2-3中鉴权方式，在这里使用新的鉴权方法
     * @param url
     * @param entity
     * @return
     */
    public ClassicHttpRequest createPostRequestV3(String url, HttpEntity entity) {
        String requestURL = this.GatewayV2 + this.ConsoleOpenAPIPrefix + this.ConsoleOpenAPIVersion + url;
        LOGGER.log(Level.FINE, "requestURL: " + requestURL);
        HttpPost httpPost = new HttpPost(requestURL);
        httpPost.setHeader("X-Authorization", this.SecretKey);
        httpPost.setHeader("X-Appbuilder-Origin", "appbuilder_sdk");
        String platform = System.getenv("APPBUILDER_SDK_PLATFORM") != null ? System.getenv("APPBUILDER_SDK_PLATFORM") : "unknown";
        httpPost.setHeader("X-Appbuilder-Sdk-Config", "{\"appbuilder_sdk_version\":\"1.0.4\",\"appbuilder_sdk_language\":\"java\",\"appbuilder_sdk_platform\":\"" + platform + "\"}");
        httpPost.setHeader("X-Appbuilder-Request-Id", UUID.randomUUID().toString());
        httpPost.setEntity(entity);
        String headers = "headers: \n";
        Header[] var7 = httpPost.getHeaders();
        int var8 = var7.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            Header header = var7[var9];
            headers = headers + header + "\n";
        }

        LOGGER.log(Level.FINE, "\n" + headers);
        return httpPost;
    }
}
