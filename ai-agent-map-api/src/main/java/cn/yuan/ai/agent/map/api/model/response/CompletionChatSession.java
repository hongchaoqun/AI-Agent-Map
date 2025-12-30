package cn.yuan.ai.agent.map.api.model.response;

import lombok.Data;

/**
 * @author hongcq
 * @since 2025/11/13
 */
@Data
public class CompletionChatSession {
    private String chatSessionId;
    private Long userId;
    private String appId;
    private String chatSessionName;
    private Integer chatSessionType;
    private Integer chatSessionStatus;
    private String productId;
}
