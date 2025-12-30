package cn.yuan.agent.map.ab.service;

import cn.yuan.agent.map.ab.config.AgentMapAbV3Properties;
import cn.yuan.agent.map.ab.local.abClient.LocalAbClient;
import com.baidubce.appbuilder.base.exception.AppBuilderServerException;
import com.baidubce.appbuilder.model.appbuilderclient.AppBuilderClientIterator;
import com.baidubce.appbuilder.model.appbuilderclient.AppBuilderClientRunRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hongcq
 * @since 2025/11/13
 */
@Component
public class AbClientService {

    @Autowired
    private AgentMapAbV3Properties agentMapAbProperties;


    /**
     * 创建会话
     * @param token ab平台的API-KEY
     * @param appID ab平台的 appID
     * @return
     * @throws IOException
     * @throws AppBuilderServerException
     */
    public String createConversation(String token, String appID) throws IOException, AppBuilderServerException {
        LocalAbClient client = new LocalAbClient(token, agentMapAbProperties.getUrl(), agentMapAbProperties.getVersion(), appID);
        return client.createConversation();
    }

    public AppBuilderClientIterator runByBody(String token, String appID, AppBuilderClientRunRequest requestBody) throws IOException, AppBuilderServerException {
        LocalAbClient client = new LocalAbClient(token, agentMapAbProperties.getUrl(), agentMapAbProperties.getVersion(), appID);
        return client.run(requestBody);
    }


}
