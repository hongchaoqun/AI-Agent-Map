package cn.yuan.ai.agent.map.api.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongcq
 * @since 2025/10/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionCreateSessionParam {

    // 会话名称
    private String chatSessionName;

    // 会话类型
    private Integer chatSessionType;

    // 智能体id, 有的平台是 agentId
    private String appId;
}
