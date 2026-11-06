package io.github.crypt_loli.loli_onebot.server.forward

import io.github.crypt_loli.loli_onebot.server.OneBotServer
import io.github.crypt_loli.loli_onebot.server.forward.module.ForwardWSConnection
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.createRouteScopedPlugin
import io.ktor.server.application.install
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.engine.embeddedServer
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText

/**
 * 把 OneBot 实现端正向 WebSocket 挂到已有的 ktor-server 上。
 * 调用前请先安装 WebSockets 插件。应用会作为客户端连进来。
 */
fun Route.applyOneBotServer(
    path: String = "/",
    accessToken: String? = null,
    system: OneBotServer
) {
    route(path) {
        accessToken?.takeIf { it.isNotEmpty() }?.also { token ->
            install(createRouteScopedPlugin("OneBotAuth") {
                onCall { call ->
                    val auth = call.request.headers[HttpHeaders.Authorization] ?: run {
                        call.respond(HttpStatusCode.Unauthorized)
                        return@onCall
                    }
                    if (auth != "Bearer $token") {
                        call.respond(HttpStatusCode.Forbidden)
                        return@onCall
                    }
                }
            })
        }
        webSocket {
            val connection = ForwardWSConnection(system, this)
            system.onConnection(connection.api) {
                runCatching {
                    for (frame in incoming) {
                        when (frame) {
                            is Frame.Text -> connection.onTextReceived(frame.readText())
                            else -> {}
                        }
                    }
                }
                system.onDisconnect(connection.api)
            }
        }
    }
}

fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> OneBotServer.createForwardServer(
    factory: ApplicationEngineFactory<TEngine, TConfiguration>,
    host: String = "0.0.0.0",
    port: Int = 6700,
    path: String = "/",
    accessToken: String? = null
) = embeddedServer(factory = factory, host = host, port = port) {
    install(WebSockets)
    routing {
        applyOneBotServer(path, accessToken, this@createForwardServer)
    }
}

suspend fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> OneBotServer.createForwardServerAndStart(
    factory: ApplicationEngineFactory<TEngine, TConfiguration>,
    host: String = "0.0.0.0",
    port: Int = 6700,
    path: String = "/",
    accessToken: String? = null
) = createForwardServer(factory, host, port, path, accessToken).startSuspend(wait = false)
