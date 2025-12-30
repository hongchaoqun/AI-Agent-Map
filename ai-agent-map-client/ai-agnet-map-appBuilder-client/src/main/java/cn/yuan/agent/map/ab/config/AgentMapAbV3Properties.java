package cn.yuan.agent.map.ab.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongcq
 * @since 2025/11/13
 */
@Data
@ConfigurationProperties(prefix = "agent.map.client.ab")
public class AgentMapAbV3Properties {

    private String url;

    private String version;
}
