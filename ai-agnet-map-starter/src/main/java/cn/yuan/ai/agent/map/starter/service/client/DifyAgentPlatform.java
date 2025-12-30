package cn.yuan.ai.agent.map.starter.service.client;

import cn.yuan.ai.agent.map.api.model.param.CompletionClientRunParam;
import cn.yuan.ai.agent.map.api.model.param.CompletionCreateSessionParam;
import cn.yuan.ai.agent.map.api.model.vo.ChatSessionVO;
import cn.yuan.ai.agent.map.starter.common.constant.AgentPlatformEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author hongcq
 * @since 2025/10/13
 */
@Service
public class DifyAgentPlatform implements IAgentPlatform{

    @Override
    public String getPlatformName() {
        return AgentPlatformEnum.DIFY.getResourceCode();
    }

    /**
     * dify 没有创建对话接口，直接返回空字符串
     * @param createSessionParam
     * @return
     */
    @Override
    public ChatSessionVO createConversation(CompletionCreateSessionParam createSessionParam) {
        return null;
    }

    @Override
    public Object sendMessage(CompletionClientRunParam message) {
        return null;
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
        return null;
    }
}
