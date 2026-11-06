package io.github.crypt_loli.loli_onebot.server.forward

import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.github.crypt_loli.loli_onebot.server.forward.module.ForwardWSConnection
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*

/**
 * 把 OneBot 实现端正向 WebSocket 挂到已有的 ktor-server 上。
 * 调用前请先安装 WebSockets 插件。应用会作为客户端连进来。
 */
fun Route.applyOneBotServer(
    system: LoliOneBotServerForwardWS,
    path: String = "/"
) {
    route(path) {
        install(createRouteScopedPlugin("OneBotAuth") {
            onCall { call ->
                system.onAnyWSConnected(call)
            }
        })
        webSocket {
            val connection = ForwardWSConnection(system.server, this)
            system.server.onConnection(connection.api) {
                runCatching {
                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> connection.onTextReceived(frame.readText())
                            else -> {}
                        }
                    }
                }
                system.server.onDisconnect(connection.api)
            }
        }
    }
}

fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> OneBotServer.createForwardServer(
    factory: ApplicationEngineFactory<TEngine, TConfiguration>,
    system: LoliOneBotServerForwardWS,
    host: String = "0.0.0.0",
    port: Int = 6700,
    path: String = "/"
) = embeddedServer(factory = factory, host = host, port = port) {
    install(WebSockets)
    routing {
        applyOneBotServer(system, path)
    }
}

suspend fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> OneBotServer.createForwardServerAndStart(
    factory: ApplicationEngineFactory<TEngine, TConfiguration>,
    system: LoliOneBotServerForwardWS,
    host: String = "0.0.0.0",
    port: Int = 6700,
    path: String = "/"
) = createForwardServer(factory, system, host, port, path).startSuspend(wait = false)
