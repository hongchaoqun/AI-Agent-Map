package cn.yuan.ai.agent.map.api.model.response;

import lombok.Data;

import java.util.Map;

/**
 * @author hongcq
 * @since 2025/12/8
 */
@Data
public class CompletionEvent {

    private String code;
    private String message;
    private String eventType;
    private String status;
    private String contentType;
    private Map<String, Object> detail;
    private Map<String, Object> usage;
    //private ToolCall[] toolCalls;
}
