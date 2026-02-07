package io.github.lingerjab.inputtips.state

import io.github.lingerjab.inputtips.win32.Imm32Util
import java.awt.Toolkit
import java.awt.event.KeyEvent

class WindowsInputStateProvider : InputStateProvider {

    /**
     * 获取当前输入法状态
     *
     * @return 输入法状态
     */
    override fun getCurrentState(): InputState {
        if (isCapsLockOn()) return InputState.CAPS

        return if (Imm32Util.isChineseInput()) {
            InputState.CN
        } else {
            InputState.EN
        }
    }
}

/**
 * 判断 Caps Lock 是否开启
 *
 * @return true = 开启，false = 关闭
 */
private fun isCapsLockOn(): Boolean {
    return try {
        Toolkit.getDefaultToolkit()
            .getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
    } catch (_: Exception) {
        false
    }
}
