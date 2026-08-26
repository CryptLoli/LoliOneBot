package io.github.crypt_loli.loli_onebot.server

import io.github.crypt_loli.loli_onebot.server.module.ReverseWSClient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch

/**
 * 附加到外部 ktor-server 上
 * 请务必确保该实例已安装 WebSockets 插件
 */
fun Route.applyLoliOneBotServer(
    path: String = "/",
    accessToken: String? = null,
    system: LoliOneBotServer
) {
    route(path) {
        accessToken?.takeIf { it.isNotEmpty() }?.also { accessToken ->
            install(createRouteScopedPlugin("AuthPlugin") {
                onCall { call ->
                    val auth = call.request.headers[HttpHeaders.Authorization] ?: run {
                        call.respond(HttpStatusCode.Unauthorized)
                        return@onCall
                    }

                    if (auth != "Bearer $accessToken") {
                        call.respond(HttpStatusCode.Forbidden)
                        return@onCall
                    }
                }
            })
        }
        webSocket {
            val client = ReverseWSClient(system, this)
            system.clients += client
            system.listener.onClientOnline(client)

            runCatching {
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> client.onTextReceived(frame.readText())
                        else -> {}
                    }
                }
            }

            system.clients -= client
            system.listener.onClientOffline(client)
        }
    }
}

fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> LoliOneBotServer.createWSServer(
    factory: ApplicationEngineFactory<TEngine, TConfiguration>,
    host: String = "0.0.0.0",
    port: Int = 8080,
    path: String = "/",
    accessToken: String? = null
) = embeddedServer(factory = factory, host = host, port = port) {
    install(WebSockets)
    routing {
        applyLoliOneBotServer(path, accessToken, this@createWSServer)
    }
}

suspend fun <TEngine : ApplicationEngine, TConfiguration : ApplicationEngine.Configuration> LoliOneBotServer.createWSServerAndStart(
    factory: ApplicationEngineFactory<TEngine, TConfiguration>,
    host: String = "0.0.0.0",
    port: Int = 8080,
    path: String = "/",
    accessToken: String? = null
) = createWSServer(factory, host, port, path, accessToken).startSuspend(wait = false)