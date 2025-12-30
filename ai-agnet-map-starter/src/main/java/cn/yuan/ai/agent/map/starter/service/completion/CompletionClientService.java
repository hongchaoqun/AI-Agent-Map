package cn.yuan.ai.agent.map.starter.service.completion;

import cn.yuan.ai.agent.map.api.ICompletionClient;
import cn.yuan.ai.agent.map.api.model.param.CompletionClientRunParam;
import cn.yuan.ai.agent.map.api.model.param.CompletionCreateSessionParam;
import cn.yuan.ai.agent.map.api.model.vo.ChatSessionVO;
import cn.yuan.ai.agent.map.starter.common.constant.AgentPlatformEnum;
import cn.yuan.ai.agent.map.starter.common.utils.AgentProductSessionUtils;
import cn.yuan.ai.agent.map.starter.config.SSEHelper;
import cn.yuan.ai.agent.map.starter.service.client.AbstractClientPlatform;
import cn.yuan.ai.agent.map.starter.service.client.IAgentPlatform;
import com.baidubce.appbuilder.model.appbuilderclient.AppBuilderClientIterator;
import com.baidubce.appbuilder.model.appbuilderclient.AppBuilderClientResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;


/**
 * @author hongcq
 * @since 2025/10/13
 */
@Service
@Slf4j
public class CompletionClientService extends AbstractClientPlatform implements ICompletionClient {

    public CompletionClientService(List<IAgentPlatform> platforms){
        super(platforms);
    }

    private final java.util.concurrent.ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    @Resource
    private SSEHelper sseHelper;

    @Override
    public ChatSessionVO createConversation(CompletionCreateSessionParam createSessionParam) {
        return getPlatform().createConversation(createSessionParam);
    }

    @Override
    public Object sendMessage(CompletionClientRunParam message) {
        return getPlatform().sendMessage(message);
    }

    @Override
    public Object uploadFile(Object file) {
        return null;
    }

    @Override
    public void stop(Object message) {

    }

    @Override
    public void submitFeedback(Object feedback) {

    }

    @Override
    public List<Object> getFollowUpQuestions(String context) {
        return List.of();
    }

    @Override
    public List<Object> getChatHistory(String sessionId) {
        return List.of();
    }

    @Override
    public List<Object> getUserConversations(String userId) {
        return List.of();
    }

    @Override
    public void removeSession(String sessionId) {

    }

    @Override
    public void updateSessionName(Object sessions) {

    }

    @Override
    public Object convertSpeechToText(Object audio) {
        return null;
    }

    @Override
    public Object convertTextToSpeech(String text) {
        return null;
    }

    @Override
    public Object getApplicationInfo() {
        return null;
    }

    @Override
    public Object getApplicationConfig() {
        return null;
    }

    @Override
    public Object getApplicationMetadata() {
        return null;
    }

    @Override
    public Object sendMessageStream(CompletionClientRunParam message, Long sseId) {
        return getPlatform().sendMessageStream(message, sseId);
    }

    /**
     * 流式对话接口特殊处理
     * @param message
     * @param sseId
     * @param sseEmitter
     */
    public void streamChat(CompletionClientRunParam message, Long sseId, SseEmitter sseEmitter){
        if(AgentProductSessionUtils.getProduct().equals(AgentPlatformEnum.APP_BUILDER_V3.getResourceCode())){
            AppBuilderClientIterator iterator = (AppBuilderClientIterator) getPlatform().sendMessageStream(message, sseId);
            
            executorService.submit(() -> {
                try{
                    while (iterator.hasNext()) {
                        AppBuilderClientResult chunk = iterator.next();
                        sendData(sseEmitter, chunk);
                    }
                    sseEmitter.complete();
                } catch (Exception e) {
                    log.error("streamChat error", e);
                    sseEmitter.completeWithError(e);
                } finally {
                    iterator.close();
                    sseHelper.stopChatStream(sseId.toString());
                }
            });
        }
    }

    public void sendData(SseEmitter sseEmitter, Object data) {
        try {
            sseEmitter.send(SseEmitter.event()
                    .data(data)
                    .reconnectTime(3000));
        } catch (IllegalStateException e) {
            // sse已经关闭了
            log.warn("sseEmitter: illegal state, probably already closed", e);
            throw new RuntimeException();
        } catch (IOException e) {
            // 这里可能是客户端中断了
            log.warn("sseEmitter: io exception, send sse data", e);
            throw new RuntimeException();
        }
    }
}
