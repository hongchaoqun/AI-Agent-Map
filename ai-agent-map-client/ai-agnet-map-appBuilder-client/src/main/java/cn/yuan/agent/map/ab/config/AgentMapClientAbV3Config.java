package cn.yuan.agent.map.ab.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hongcq
 * @since 2025/11/13
 */
@EnableScheduling
@EnableConfigurationProperties({AgentMapAbV3Properties.class})
@ComponentScan("cn.yuan.agent.map.ab")
@Configuration
public class AgentMapClientAbV3Config {
}
