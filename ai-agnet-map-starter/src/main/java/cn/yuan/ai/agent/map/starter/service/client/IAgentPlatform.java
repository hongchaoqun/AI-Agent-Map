package cn.yuan.ai.agent.map.starter.service.client;

import cn.yuan.ai.agent.map.api.ICompletionClient;

/**
 * 协议加工层接口
 * @author hongcq
 * @since 2025/10/13
 */
public interface IAgentPlatform extends ICompletionClient {

    String getPlatformName();

}
