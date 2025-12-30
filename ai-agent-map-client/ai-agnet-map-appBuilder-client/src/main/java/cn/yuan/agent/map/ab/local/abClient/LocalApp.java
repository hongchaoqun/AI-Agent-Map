package cn.yuan.agent.map.ab.local.abClient;

import cn.yuan.agent.map.ab.local.ComponentLocal;
import com.baidubce.appbuilder.base.exception.AppBuilderServerException;
import com.baidubce.appbuilder.base.utils.http.HttpResponse;
import com.baidubce.appbuilder.base.utils.json.JsonUtils;
import com.baidubce.appbuilder.model.appbuilderclient.AppDescribeRequest;
import com.baidubce.appbuilder.model.appbuilderclient.AppDescribeResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author hongcq
 * @since 2025/11/13
 */
public class LocalApp extends ComponentLocal {

    public LocalApp(String token, String baseUrl, String version){
        super(token, baseUrl, version);
    }

    public AppDescribeResponse describeApp(String appId) throws IOException, AppBuilderServerException {
        String url = "/app?Action=DescribeApp";
        AppDescribeRequest request = new AppDescribeRequest();
        request.setId(appId);
        String jsonBody = JsonUtils.serialize(request);
        ClassicHttpRequest postRequest = this.httpClient.createPostRequestV2(url, new StringEntity(jsonBody, StandardCharsets.UTF_8));
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");
        HttpResponse<AppDescribeResponse> response = this.httpClient.execute(postRequest, AppDescribeResponse.class);
        AppDescribeResponse respBody = (AppDescribeResponse)response.getBody();
        return respBody;
    }
}
