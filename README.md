<div align="center"><h1>LoliOneBot</h1></div>

LoliOneBot 是一个使用 Kotlin跨平台 实现的轻量 OneBot SDK

项目处于早期阶段, 可能会出现大幅度重构

## 说说优点?

- 可以将其附加到现有的 ktor-server 实例上 (而不像某些实现必须额外开启 http 端口)
- 反向 WebSocket 支持多个客户端同时连接
- 可以按需导入需要的模块, 减少项目体积

## 功能列表

- 🟩 应用层 (作为客户端连接OneBot实现)
    - 🟥 双向 HTTP
    - 🟩 正向 Websocket
    - 🟩 反向 Websocket
  
- 🟩 服务层 (作为OneBot实现被客户端连接)
  - 🟥 双向 HTTP
  - 🟥 正向 Websocket
  - 🟩 反向 Websocket

## 作为库使用

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // 把 ${version} 替换成最新版本
    // 应用端
    implementation("io.github.cryptloli:loli-onebot-client:${version}")
    implementation("io.github.cryptloli:loli-onebot-client-network-forward-ws:${version}")
    implementation("io.github.cryptloli:loli-onebot-client-network-reverse-ws:${version}")
    implementation("io.github.cryptloli:loli-onebot-kotlin-dsl:${version}")

    // forward-ws, 需要一个 ktor client 引擎
    // https://ktor.io/docs/client-engines.html
    implementation("io.ktor:ktor-client-java:${ktorVersion}")
    // reverse-ws, 需要一个 ktor server 引擎
    // https://ktor.io/docs/server-engines.html
    implementation("io.ktor:ktor-server-cio:${ktorVersion}")
}
```

## 快速开始

这是一个简单的示范 适用于 `kotlin/jvm`

```kotlin
// TODO
```

## 一些常见问题

Q: 你们不会自动配置吗, 为什么必须手动添加一个 ktor 引擎?

A: 为了为使用者让出更大的自由空间

## Issues

如果你发现了 bug 或者需要新的功能, 你可以创建一个新的 issue

我们也欢迎您提交 pull request
