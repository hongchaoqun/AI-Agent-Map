package cn.yuan.ai.agent.map.api.model.vo;

import lombok.Data;

/**
 * @author hongcq
 * @since 2025/12/5
 */
@Data
public class ChatSessionVO {

    private String id;

    private String requestsId;

    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * appId 或者 agentId
     */
    private String appId;

    /**
     * 会话名称
     */
    private String chatSessionName;

    /**
     * 会话类型
     */
    private Integer chatSessionType;
}
