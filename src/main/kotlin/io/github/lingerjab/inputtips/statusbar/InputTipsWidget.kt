package io.github.lingerjab.inputtips.statusbar

import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import io.github.lingerjab.inputtips.sevice.InputStateService
import io.github.lingerjab.inputtips.state.WindowsInputStateProvider
import java.awt.Component
import javax.swing.Icon

class InputTipsWidget : StatusBarWidget,
    StatusBarWidget.TextPresentation, StatusBarWidget.IconPresentation {

    companion object {
        const val ID = "InputTipsWidget"
    }

    private val stateService = InputStateService(WindowsInputStateProvider(), ID)
    private val inputState get() = stateService.currentState

    override fun ID() = ID

    override fun getPresentation(): StatusBarWidget.WidgetPresentation = this

    override fun getText(): String = inputState.displayText

    override fun getIcon(): Icon = inputState.displayIcon

    override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

    override fun getTooltipText(): @NlsContexts.Tooltip String =
        "当前：${inputState.statusName}"


    override fun install(statusBar: StatusBar) {
        stateService.install(statusBar)
    }

    override fun dispose() {
        stateService.dispose()
    }
}