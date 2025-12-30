package cn.yuan.ai.agent.map.starter.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hongcq
 * @since 2025/12/8
 */
@Slf4j
@Component
public class SSEHelper {

    private final Cache<String, SseEmitter> sseEmitterMap = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES).build();

    public SseEmitter getSseEmitter(Long sseEmitterId, long timeout) {
        // 默认30秒超时,设置为0L则永不超时
        SseEmitter sseEmitter = new SseEmitter(timeout);
        try {
            Map<String, String> data = new HashMap<>();
            data.put("sseId", String.valueOf(sseEmitterId));
            data.put("event", "sse");
            sseEmitter.send(SseEmitter.event()
                    .data(data)
                    .reconnectTime(3000));
        } catch (IOException e) {
            log.error("sseEmitter send", e);
            throw new RuntimeException();
        }
        log.info("sseEmitterId: {}", sseEmitterId);
        sseEmitterMap.put(sseEmitterId.toString(), sseEmitter);
        sseEmitter.onCompletion(() -> sseEmitterMap.invalidate(String.valueOf(sseEmitterId)));
        return sseEmitter;
    }

    public SseEmitter stopChatStream(String sseId) {
        SseEmitter sseEmitter = sseEmitterMap.getIfPresent(sseId);
        if (sseEmitter == null) {
            return null;
        }
        sseEmitterMap.invalidate(sseId);
        return sseEmitter;
    }
}
