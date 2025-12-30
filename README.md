# AI Agent Map

AI Agent Map 是一个统一的智能体平台接口封装工具，旨在简化开发者对接多个AI智能体平台的过程。通过统一的API接口，开发者可以轻松地与不同的智能体平台进行交互，无需为每个平台单独开发适配代码。

## 项目概述

AI Agent Map 是一个中间件项目，它将不同AI智能体平台（如Coze、Dify、百度AppBuilder等）的API接口进行统一抽象和封装，提供一致的接口供上层应用调用。项目采用Spring Boot框架开发，支持多平台动态切换。

## 核心功能

- **统一接口**: 为不同AI平台提供统一的API接口
- **多平台支持**: 目前支持蓝翼问之、Dify、百度AppBuilder等多个平台
- **动态切换**: 通过请求头动态选择目标AI平台
- **会话管理**: 支持对话创建、消息发送、历史记录等会话管理功能
- **流式响应**: 支持SSE流式响应，适用于实时对话场景

## 支持的平台

- **蓝翼问之** (lanyi_wenzhi)
- **Dify** (dify)
- **百度AppBuilder** (app_builder)
- **百度AppBuilder 3.0版本** (app_builder_v3)

## 主要特性

- 统一的智能体对话接口
- 会话创建与管理
- 消息发送与接收
- 流式对话支持
- 文件上传功能
- 对话历史记录
- 会话反馈机制

## API接口

### 创建会话

```
POST /ai-agent/completion/create/session
```

请求参数:
```json
{
  "chatSessionName": "会话名称",
  "chatSessionType": 1,
  "appId": "智能体ID"
}
```

### 发送消息

```
POST /ai-agent/completion/runs
```

请求参数:
```json
{
  "appId": "智能体ID",
  "query": "用户输入的消息",
  "stream": true,
  "conversationId": "会话ID",
  "endUserId": "用户ID"
}
```

## 平台选择

通过请求头 `Platform-Product` 指定目标平台:

```
Platform-Product: dify          # 选择Dify平台
Platform-Product: app_builder   # 选择百度AppBuilder
Platform-Product: app_builder_v3 # 选择百度AppBuilder 3.0
Platform-Product: lanyi_wenzhi   # 选择蓝翼问之（默认）
```

认证信息通过 `Platform-Authorization` 请求头传递:

```
Platform-Authorization: your_token_here
```

## 项目结构

```
ai-agent-map/
├── ai-agent-map-api/          # API接口定义
├── ai-agent-map-app/          # 应用启动模块
├── ai-agent-map-client/       # 各平台客户端实现
│   ├── ai-agnet-map-appBuilder-client/  # 百度AppBuilder客户端
│   ├── ai-agent-map-coze-client/        # Coze客户端
│   ├── ai-agent-map-dify-cilent/        # Dify客户端
│   └── ai-agent-map-springAI-cilent/    # SpringAI客户端
└── ai-agnet-map-starter/      # 核心启动器模块
```

## 快速开始

1. 启动应用:
```bash
mvn spring-boot:run
```

2. 调用API时指定平台:
```bash
curl -X POST http://localhost:8080/ai-agent/completion/runs \
  -H "Content-Type: application/json" \
  -H "Platform-Product: dify" \
  -H "Platform-Authorization: your_token" \
  -d '{
    "appId": "your_app_id",
    "query": "Hello, how are you?",
    "stream": true
  }'
```

## 配置

在 `application.yaml` 中配置各平台的地址:

```yaml
agent:
  map:
    client:
      ab: # 百度AppBuilder配置
        url: https://lanyi-agent.ccccltd.cn/api/ai_apaas
        version: /v1
      dify: # Dify配置
        url: http://106.63.7.106:10001/build_agent/v1
```

## 技术栈

- Java 17
- Spring Boot 3.4.2
- Maven
- Lombok
- MapStruct

## 扩展性

项目设计具有良好的扩展性，可以轻松添加新的AI平台支持：

1. 实现 `IAgentPlatform` 接口
2. 添加对应的配置和客户端代码
3. 在枚举中添加平台类型

## 贡献

欢迎提交Issue和Pull Request来改进项目。
