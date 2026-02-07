package io.github.lingerjab.inputtips.statusbar

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory

class InputStatusWidgetFactory : StatusBarWidgetFactory {
    override fun getId(): String = "InputStatusWidget"

    override fun getDisplayName() = "输入状态"

    override fun isAvailable(project: Project) = true

    override fun createWidget(project: Project): StatusBarWidget = InputTipsWidget()

    override fun disposeWidget(widget: StatusBarWidget) {
//        widget.dispose()
    }

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true
}