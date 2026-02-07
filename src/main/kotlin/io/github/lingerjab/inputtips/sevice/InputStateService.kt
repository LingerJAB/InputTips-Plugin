package io.github.lingerjab.inputtips.sevice

import com.intellij.openapi.wm.StatusBar
import com.intellij.util.Alarm
import io.github.lingerjab.inputtips.state.InputState
import io.github.lingerjab.inputtips.state.InputStateProvider

class InputStateService(
    private val provider: InputStateProvider,
    private val widgetId: String
) {
    var currentState: InputState = InputState.EN
        private set

    private val alarm = Alarm(Alarm.ThreadToUse.SWING_THREAD)
    private var statusBar: StatusBar? = null


    fun install(statusBar: StatusBar) {
        this.statusBar = statusBar
        start()
    }

    private fun start() {
        alarm.addRequest(object : Runnable {
            override fun run() {
                val state = provider.getCurrentState()
                if (currentState != state) { // 状态改变
                    currentState = state
//                    println(state.displayText)
                    statusBar?.updateWidget(widgetId)
                }
                alarm.addRequest(this, 200)
            }
        }, 100)
    }

    fun dispose() {
        alarm.cancelAllRequests()
    }
}