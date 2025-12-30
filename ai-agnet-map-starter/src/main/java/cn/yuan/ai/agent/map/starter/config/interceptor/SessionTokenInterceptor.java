package cn.yuan.ai.agent.map.starter.config.interceptor;

import cn.yuan.ai.agent.map.starter.common.constant.AgentMapHeaders;
import cn.yuan.ai.agent.map.starter.common.utils.AgentAuthSessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author hongcq
 * @since 2025/10/14
 */
@Slf4j
@Component
public class SessionTokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        AgentAuthSessionUtils.init();
        String token = request.getHeader(AgentMapHeaders.AUTH_TOKEN);
        AgentAuthSessionUtils.setToken(token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AgentAuthSessionUtils.clear();
    }

}
