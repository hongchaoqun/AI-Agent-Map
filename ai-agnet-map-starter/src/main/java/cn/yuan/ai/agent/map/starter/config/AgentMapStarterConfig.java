package cn.yuan.ai.agent.map.starter.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hongcq
 * @since 2025/10/14
 */
@EnableScheduling
@EnableConfigurationProperties({AgentMapProperties.class})
@ComponentScan("cn.yuan.ai.agent.map.starter")
@Configuration
public class AgentMapStarterConfig {
}
