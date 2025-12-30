package cn.yuan.ai.agent.map.starter.config;

import cn.yuan.ai.agent.map.starter.config.interceptor.AgentProductInterceptor;
import cn.yuan.ai.agent.map.starter.config.interceptor.SessionTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hongcq
 * @since 2025/10/14
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionTokenInterceptor sessionTokenInterceptor;
    @Autowired
    private AgentProductInterceptor agentProductInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionTokenInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(agentProductInterceptor)
                .addPathPatterns("/**");
    }
}
