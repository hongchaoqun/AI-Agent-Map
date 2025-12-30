package cn.yuan.agent.map.dify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongcq
 * @since 2025/11/13
 */
@Data
@ConfigurationProperties(prefix = "agent.map.client.dify")
public class AgentMapDifyProperties {

    private String url;
}
