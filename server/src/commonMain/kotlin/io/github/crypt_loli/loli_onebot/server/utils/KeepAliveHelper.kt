package io.github.crypt_loli.loli_onebot.server.utils

import io.github.crypt_loli.loli_onebot.events.OneBotHeartbeatEvent
import io.github.crypt_loli.loli_onebot.server.OneBotServer
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration

class KeepAliveHelper(
    val server: OneBotServer,
    interval: Duration
) {
    var job: Job? = null
        private set
    val isRunning
        get() = job?.isActive == true
    var interval = interval
        set(value) {
            job?.cancel()
            job = null

            if (value.isPositive()) {
                server.scope.launch {
                    while (isActive) {
                        delay(interval)

                        server.broadcast(
                            event = OneBotHeartbeatEvent(
                                interval = interval.inWholeMilliseconds,
                                status = OneBotHeartbeatEvent.Status(server.account.online)
                            )
                        )
                    }
                }
            }

            field = value
        }
}