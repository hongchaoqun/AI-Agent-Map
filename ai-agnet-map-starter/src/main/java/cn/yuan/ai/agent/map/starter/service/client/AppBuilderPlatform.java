package cn.yuan.ai.agent.map.starter.service.client;


import cn.yuan.agent.map.ab.service.AbClientService;
import cn.yuan.ai.agent.map.api.model.param.CompletionClientRunParam;
import cn.yuan.ai.agent.map.api.model.param.CompletionCreateSessionParam;
import cn.yuan.ai.agent.map.api.model.vo.ChatSessionVO;
import cn.yuan.ai.agent.map.starter.common.constant.AgentPlatformEnum;
import cn.yuan.ai.agent.map.starter.common.utils.AgentAuthSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author hongcq
 * @since 2025/10/13
 */
@Service
public class AppBuilderPlatform implements IAgentPlatform{

    @Autowired
    private AbClientService abClientService;

    @Override
    public String getPlatformName() {
        return AgentPlatformEnum.APP_BUILDER.getResourceCode();
    }

    @Override
    public ChatSessionVO createConversation(CompletionCreateSessionParam createSessionParam) {
        try {
            ChatSessionVO chatSessionVO = new ChatSessionVO();
            chatSessionVO.setConversationId(abClientService.createConversation(AgentAuthSessionUtils.getToken(), createSessionParam.getAppId()));
            return chatSessionVO;
        }catch (Exception e){
            e.printStackTrace();
        }
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
