package cn.yuan.ai.agent.map.api;


import cn.yuan.ai.agent.map.api.model.param.CompletionClientRunParam;
import cn.yuan.ai.agent.map.api.model.param.CompletionCreateSessionParam;
import cn.yuan.ai.agent.map.api.model.vo.ChatSessionVO;

import java.util.List;

/**
 * 文本生成型智能体
 * @author hongcq
 * @since 2025/10/13
 */
public interface ICompletionClient {

    // 新建对话
    ChatSessionVO createConversation(CompletionCreateSessionParam createSessionParam);

    // 发送对话消息
    Object sendMessage(CompletionClientRunParam message);

    Object sendMessageStream(CompletionClientRunParam message, Long sseId);

    // 上传文件
    Object uploadFile(Object file);

    // 停止响应
    void stop(Object message);

    // 消息反馈（点赞）
    void submitFeedback(Object feedback);

    // 获取下一轮建议问题列表
    List<Object> getFollowUpQuestions(String context);

    // 获取会话历史消息
    List<Object> getChatHistory(String sessionId);

    // 获取会话列表
    List<Object> getUserConversations(String userId);

    // 删除会话
    void removeSession(String sessionId);

    // 会话重命名
    void updateSessionName(Object sessions);

    // 语音转文字
    Object convertSpeechToText(Object audio);

    // 文字转语音
    Object convertTextToSpeech(String text);

    // 获取应用基本信息
    Object getApplicationInfo();

    // 获取应用参数
    Object getApplicationConfig();

    // 获取应用Meta信息
    Object getApplicationMetadata();
}
