package cn.yuan.ai.agent.map.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongcq
 * @since 2025/10/14
 */
@Data
@ConfigurationProperties(prefix = "agent.map")
public class AgentMapProperties {

    private String url;
}
