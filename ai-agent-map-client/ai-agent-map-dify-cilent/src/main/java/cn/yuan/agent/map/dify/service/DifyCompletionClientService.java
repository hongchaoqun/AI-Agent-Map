package cn.yuan.agent.map.dify.service;

import cn.yuan.agent.map.dify.config.AgentMapDifyProperties;
import io.github.imfangs.dify.client.DifyClientFactory;
import io.github.imfangs.dify.client.DifyCompletionClient;
import io.github.imfangs.dify.client.exception.DifyApiException;
import io.github.imfangs.dify.client.model.DifyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hongcq
 * @since 2025/11/13
 */
@Component
public class DifyCompletionClientService {

    @Autowired
    private AgentMapDifyProperties agentMapDifyProperties;

    public String createConversation(String token) throws DifyApiException, IOException {
        // 使用自定义配置创建客户端
        DifyConfig config = DifyConfig.builder()
                .baseUrl(agentMapDifyProperties.getUrl())
                .apiKey(token)
                .connectTimeout(5000)
                .readTimeout(60000)
                .writeTimeout(30000)
                .build();

        DifyCompletionClient completionClient = DifyClientFactory.createCompletionClient(config);

        return "";
    }
}
