package cn.yuan.ai.agent.map.starter.config.interceptor;


import cn.yuan.ai.agent.map.starter.common.constant.AgentMapHeaders;
import cn.yuan.ai.agent.map.starter.common.constant.AgentPlatformEnum;
import cn.yuan.ai.agent.map.starter.common.utils.AgentAuthSessionUtils;
import cn.yuan.ai.agent.map.starter.common.utils.AgentProductSessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author hongcq
 * @since 2025/12/4
 */
@Slf4j
@Component
public class AgentProductInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        AgentProductSessionUtils.init();
        String product = request.getHeader(AgentMapHeaders.BIZ_PRODUCT);
        if (!StringUtils.hasText(product)) {
            product = AgentPlatformEnum.LAN_YI_WEN_ZHI.getResourceCode();
        }
        AgentProductSessionUtils.setProduct(product);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AgentAuthSessionUtils.clear();
    }
}
