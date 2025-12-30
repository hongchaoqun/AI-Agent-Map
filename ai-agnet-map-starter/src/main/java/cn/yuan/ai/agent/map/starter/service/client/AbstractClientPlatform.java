package cn.yuan.ai.agent.map.starter.service.client;

import cn.yuan.ai.agent.map.starter.common.utils.AgentProductSessionUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author hongcq
 * @since 2025/10/13
 */
@Slf4j
public abstract class AbstractClientPlatform {

    public List<IAgentPlatform> platforms;

    public AbstractClientPlatform(List<IAgentPlatform> platforms){
        this.platforms = platforms;
    }

    public IAgentPlatform getPlatform(){
        if(platforms == null || platforms.isEmpty()){
            log.error("找不到正确的目标平台");
            throw new RuntimeException();
        }
        String product = AgentProductSessionUtils.getProduct();
        for (IAgentPlatform platform : platforms) {
            String productName = platform.getPlatformName();
            if(product.equals(productName)){
                return platform;
            }
        }
        log.error("找不到正确的目标平台");
        throw new RuntimeException();
    };
}
