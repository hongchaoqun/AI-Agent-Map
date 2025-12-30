package cn.yuan.agent.map.ab.local.abClient;

import cn.yuan.agent.map.ab.local.ComponentLocal;
import com.baidubce.appbuilder.base.exception.AppBuilderServerException;
import com.baidubce.appbuilder.base.utils.http.HttpResponse;
import com.baidubce.appbuilder.base.utils.iterator.StreamIterator;
import com.baidubce.appbuilder.base.utils.json.JsonUtils;
import com.baidubce.appbuilder.model.appbuilderclient.*;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hongcq
 * @since 2025/11/13
 */
public class LocalAbClient extends ComponentLocal {

    private String appID;

    public LocalAbClient(String token, String baseUrl, String version, String appID){
        super(token, baseUrl, version);
        this.appID = appID;
    }


    public String createConversation() throws IOException, AppBuilderServerException {
        return this.innerCreateConversation();
    }

    private String innerCreateConversation() throws IOException, AppBuilderServerException {
        String url = "/app/conversation";
        if (this.appID != null && !this.appID.isEmpty()) {
            Map<String, String> requestBody = new HashMap();
            requestBody.put("app_id", this.appID);
            String jsonBody = JsonUtils.serialize(requestBody);
            ClassicHttpRequest postRequest = this.httpClient.createPostRequestV3(url, new StringEntity(jsonBody, StandardCharsets.UTF_8));
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");
            HttpResponse<ConversationResponse> response = this.httpClient.execute(postRequest, ConversationResponse.class);
            ConversationResponse respBody = (ConversationResponse)response.getBody();
            return respBody.getConversationId();
        } else {
            throw new RuntimeException("Param 'appID' is required!");
        }
    }

    public String uploadLocalFile(String conversationId, String filePath) throws IOException, AppBuilderServerException {
        return this.innerUploadLocalFile(conversationId, filePath, "");
    }

    public String uploadFile(String conversationId, String filePath, String fileUrl) throws IOException, AppBuilderServerException {
        return this.innerUploadLocalFile(conversationId, filePath, fileUrl);
    }

    private String innerUploadLocalFile(String conversationId, String filePath, String fileUrl) throws IOException, AppBuilderServerException {
        String url = "/app/conversation/file/upload";
        if (this.appID != null && !this.appID.isEmpty()) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.LEGACY).setCharset(StandardCharsets.UTF_8);
            if (filePath != null && !filePath.isEmpty()) {
                builder.addBinaryBody("file", new File(filePath));
            } else if (fileUrl != null && !fileUrl.isEmpty()) {
                builder.addTextBody("file_url", fileUrl);
            }

            builder.addTextBody("app_id", this.appID);
            builder.addTextBody("conversation_id", conversationId);
            builder.addTextBody("scenario", "assistant");
            ClassicHttpRequest postRequest = this.httpClient.createPostRequestV3(url, builder.build());
            HttpResponse<FileUploadResponse> response = this.httpClient.execute(postRequest, FileUploadResponse.class);
            FileUploadResponse respBody = (FileUploadResponse)response.getBody();
            return respBody.getFileId();
        } else {
            throw new RuntimeException("Param 'appID' is required!");
        }
    }

    public AppBuilderClientIterator run(String query, String conversationId, String[] fileIds, boolean stream) throws IOException, AppBuilderServerException {
        String url = "/app/conversation/runs";
        if (this.appID != null && !this.appID.isEmpty()) {
            Map<String, Object> requestBody = new HashMap();
            requestBody.put("app_id", this.appID);
            requestBody.put("query", query);
            requestBody.put("conversation_id", conversationId);
            requestBody.put("file_ids", fileIds);
            requestBody.put("stream", stream);
            String jsonBody = JsonUtils.serialize(requestBody);
            ClassicHttpRequest postRequest = this.httpClient.createPostRequestV3(url, new StringEntity(jsonBody, StandardCharsets.UTF_8));
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");
            HttpResponse<StreamIterator<AppBuilderClientResponse>> response = this.httpClient.executeSSE(postRequest, AppBuilderClientResponse.class);
            return new AppBuilderClientIterator((StreamIterator)response.getBody());
        } else {
            throw new RuntimeException("Param 'appID' is required!");
        }
    }

    public AppBuilderClientIterator run(AppBuilderClientRunRequest requestBody) throws IOException, AppBuilderServerException {
        String url = "/app/conversation/runs";
        if (this.appID != null && !this.appID.isEmpty()) {
            String jsonBody = JsonUtils.serialize(requestBody);
            ClassicHttpRequest postRequest = this.httpClient.createPostRequestV3(url, new StringEntity(jsonBody, StandardCharsets.UTF_8));
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");
            HttpResponse<StreamIterator<AppBuilderClientResponse>> response = this.httpClient.executeSSE(postRequest, AppBuilderClientResponse.class);
            return new AppBuilderClientIterator((StreamIterator)response.getBody());
        } else {
            throw new RuntimeException("Param 'appID' is required!");
        }
    }

    public AppBuilderClientFeedbackResponse feedback(AppBuilderClientFeedbackRequest requestBody) throws IOException, AppBuilderServerException {
        String url = "/app/conversation/feedback";
        if (requestBody.getAppId() == null || requestBody.getAppId().isEmpty()) {
            requestBody.setAppId(this.appID);
        }

        if (requestBody.getAppId() != null && !requestBody.getAppId().isEmpty()) {
            String jsonBody = JsonUtils.serialize(requestBody);
            ClassicHttpRequest postRequest = this.httpClient.createPostRequestV3(url, new StringEntity(jsonBody, StandardCharsets.UTF_8));
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36 Edg/136.0.0.0");
            HttpResponse<AppBuilderClientFeedbackResponse> response = this.httpClient.execute(postRequest, AppBuilderClientFeedbackResponse.class);
            AppBuilderClientFeedbackResponse respBody = (AppBuilderClientFeedbackResponse)response.getBody();
            return respBody;
        } else {
            throw new RuntimeException("Param 'appID' is required!");
        }
    }
}
