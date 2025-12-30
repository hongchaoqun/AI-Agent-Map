package cn.yuan.ai.agent.map.starter.controller;

import cn.yuan.ai.agent.map.api.model.param.CompletionClientRunParam;
import cn.yuan.ai.agent.map.api.model.param.CompletionCreateSessionParam;
import cn.yuan.ai.agent.map.api.model.vo.ChatSessionVO;
import cn.yuan.ai.agent.map.starter.config.SSEHelper;
import cn.yuan.ai.agent.map.starter.service.completion.CompletionClientService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 文本生成型智能体应用接口
 * @author hongcq
 * @since 2025/10/13
 */
@RestController
@RequestMapping("/ai-agent/completion")
public class CompletionClientController {

    @Resource
    private CompletionClientService completionClientService;
    @Resource
    private SSEHelper sseHelper;

    private static final long SSE_TIMEOUT = 1000L * 600;


    @PostMapping("/create/session")
    public ChatSessionVO createSession(@RequestBody CompletionCreateSessionParam createSessionParam) {
        return completionClientService.createConversation(createSessionParam);
    }

    @PostMapping("/runs")
    public Object runConversation(@RequestBody CompletionClientRunParam runParam) {
        Long sseId = 0L;
        SseEmitter sseEmitter = sseHelper.getSseEmitter(sseId, SSE_TIMEOUT);
        completionClientService.streamChat(runParam, sseId, sseEmitter);
        return sseEmitter;
    }
}
