package cn.yuan.ai.agent.map.starter.common.constant;

import lombok.Generated;

/**
 * @author hongcq
 * @since 2025/10/15
 */
public enum AgentPlatformEnum {

    LAN_YI_WEN_ZHI("蓝翼问之", "lanyi_wenzhi"),
    DIFY("dify平台", "dify"),
    APP_BUILDER("百度appBuilder", "app_builder"),
    APP_BUILDER_V3("百度appBuilder,3.0版本", "app_builder_v3")
    ;

    private final String label;
    private final String resourceCode;

    private AgentPlatformEnum(String label, String resourceCode) {
        this.label = label;
        this.resourceCode = resourceCode;
    }

    @Generated
    public String getLabel() {
        return this.label;
    }

    @Generated
    public String getResourceCode() {
        return this.resourceCode;
    }


}
