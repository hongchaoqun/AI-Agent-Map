package cn.yuan.ai.agent.map.api.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongcq
 * @since 2025/12/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionClientRunParam {

    private String appId;
    private String query;
    private Boolean stream;
    private String conversationId;
    private String endUserId;
}
