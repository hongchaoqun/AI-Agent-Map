package cn.yuan.agent.map.ab.local.abClient;

import cn.yuan.agent.map.ab.local.ComponentLocal;
import com.baidubce.appbuilder.base.exception.AppBuilderServerException;
import com.baidubce.appbuilder.base.utils.http.HttpResponse;
import com.baidubce.appbuilder.base.utils.json.JsonUtils;
import com.baidubce.appbuilder.model.appbuilderclient.*;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author hongcq
 * @since 2025/11/13
 */
public class LocalAppList extends ComponentLocal {

    public LocalAppList(String token, String baseUrl, String version){
        super(token, baseUrl, version);
    }

    /** @deprecated */
    @Deprecated
    public App[] getAppList(AppListRequest request) throws IOException, AppBuilderServerException {
        String url = "/apps";
        ClassicHttpRequest getRequest = this.httpClient.createGetRequestV2(url, request.toMap());
        getRequest.setHeader("Content-Type", "application/json");
        getRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");

        HttpResponse<AppListResponse> response = this.httpClient.execute(getRequest, AppListResponse.class);
        AppListResponse respBody = (AppListResponse)response.getBody();
        return respBody.getData();
    }

    public AppsDescribeResponse describeApps(AppsDescribeRequest request) throws IOException, AppBuilderServerException {
        String url = "/app?Action=DescribeApps";
        String jsonBody = JsonUtils.serialize(request);
        ClassicHttpRequest postRequest = this.httpClient.createPostRequestV2(url, new StringEntity(jsonBody, StandardCharsets.UTF_8));
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");
        HttpResponse<AppsDescribeResponse> response = this.httpClient.execute(postRequest, AppsDescribeResponse.class);
        AppsDescribeResponse respBody = (AppsDescribeResponse)response.getBody();
        return respBody;
    }
}
