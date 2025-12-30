package cn.yuan.ai.agent.map.api.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author hongcq
 * @since 2025/12/8
 */
@Data
public class CompletionClientResult {

    //private String event;
    private String requestId;
    private String answer;
    private String messageId;
    private List<CompletionEvent> events;
    private String code;
    private String message;
}
