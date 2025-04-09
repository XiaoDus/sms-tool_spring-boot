
---

# SMS Tool - Spring Boot

这个项目是一个基于 Spring Boot 构建的短信发送工具，旨在提供一个简单易用、模块化、可扩展的短信服务平台。它可以集成多个短信服务商的接口，支持多种短信场景，如验证码短信、通知短信、营销短信等。

## 项目特点

- 基于 **Spring Boot** 框架，快速开发和部署。
- 提供清晰的**分层架构**，便于扩展和维护。
- 支持通过配置轻松接入不同的短信服务商。
- 配置和启动简单，易于集成到现有项目中。

## 项目结构

```
sms-tool_spring-boot/
├── .mvn/                  # Maven Wrapper 文件夹
├── src/                   # 项目源代码
│   ├── main/
│   │   ├── java/          # Java 源码
│   │   │   └── com.xiaodus.smstool/  # 主包路径
│   │   └── resources/     # 资源文件
│   │       └── application.yml       # 配置文件
├── mvnw, mvnw.cmd         # 跨平台 Maven 启动脚本
├── pom.xml                # Maven 构建配置文件
└── .gitignore             # Git 忽略文件
```

## 环境要求

- **Java 17+**
- **Maven 3.8+**

## 启动指南

1. 克隆项目代码：

   ```bash
   git clone https://github.com/XiaoDus/sms-tool_spring-boot.git
   cd sms-tool_spring-boot
   ```

2. 使用 Maven 启动项目：

   ```bash
   ./mvnw spring-boot:run
   ```

   默认会启动 Spring Boot 应用，并监听 `http://localhost:8080`。

## 未来计划

- **短信服务商集成**：目前支持接入各种短信服务商（如阿里云、腾讯云等），未来会完善集成 SDK。
- **统一异常处理**：引入全局异常处理器，提升系统健壮性。
- **限流机制**：基于 Redis 的短信发送限流，防止滥发短信。
- **Swagger 集成**：为 API 添加 Swagger 文档。
- **多租户支持**：实现多租户管理和不同通道的短信发送支持。


---
