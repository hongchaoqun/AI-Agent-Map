package cn.yuan.agent.map.ab.local;


import cn.yuan.agent.map.ab.local.utils.V3HttpClient;

/**
 * @author hongcq
 * @since 2025/11/13
 */
public class ComponentLocal {

    protected V3HttpClient httpClient;

    private static final String SECRET_KEY_PREFIX = "Bearer";


    public ComponentLocal(String token, String baseUrl, String version){
        this.initClient(token, baseUrl, version);
    }

    private String getEnvWithDefault(String propertyKey, String currentValue, String defaultValue) {
        if (currentValue == null || currentValue.isEmpty()) {
            currentValue = System.getProperty(propertyKey);
            if (currentValue == null) {
                currentValue = System.getenv(propertyKey);
            }

            if (currentValue == null) {
                currentValue = defaultValue;
            }
        }

        return currentValue;
    }

    private void initClient(String secretKey, String gateway, String version) {
        String gatewayV2 = gateway;
        if (secretKey.isEmpty()) {
            throw new RuntimeException("param secretKey is null and env APPBUILDER_TOKEN not set!");
        } else {
            String secretKeyPrefix = SECRET_KEY_PREFIX;
            if (!secretKey.startsWith(secretKeyPrefix)) {
                secretKey = String.format("%s %s", secretKeyPrefix, secretKey);
            }

            this.httpClient = new V3HttpClient(secretKey, gateway, gatewayV2);
            this.httpClient.ConsoleOpenAPIPrefix = this.getEnvWithDefault("CONSOLE_OPENAPI_PREFIX", "", "");
            this.httpClient.ConsoleOpenAPIVersion = version;
        }
    }
}
