package io.github.lingerjab.inputtips.win32

import com.sun.jna.platform.win32.User32
import com.sun.jna.ptr.IntByReference

object Imm32Util {

    /**
     * 判断当前前台窗口的 IME 是否处于“中文（Open）状态”
     *
     * @return true = 中文输入，false = 英文输入 / 检测失败
     */
    fun isChineseInput(): Boolean {
        val hWnd = User32.INSTANCE.GetForegroundWindow() ?: return false
        val hIMC = Imm32.INSTANCE.ImmGetContext(hWnd) ?: return false

        return try {
            val conv = IntByReference()
            val sent = IntByReference()
            if (!Imm32.INSTANCE.ImmGetConversionStatus(hIMC, conv, sent)) {
                false
            } else {
                conv.value and Imm32.IME_CMODE_NATIVE != 0
            }
//            Imm32.INSTANCE.ImmGetOpenStatus(hIMC)
        } finally {
            Imm32.INSTANCE.ImmReleaseContext(hWnd, hIMC)
        }
    }
}