package io.github.lingerjab.inputtips.win32

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.platform.win32.WinDef
import com.sun.jna.platform.win32.WinNT
import com.sun.jna.ptr.IntByReference

interface Imm32 : Library {

    fun ImmGetContext(hWnd: WinDef.HWND): WinNT.HANDLE?
    fun ImmReleaseContext(hWnd: WinDef.HWND, hIMC: WinNT.HANDLE): Boolean
    fun ImmGetOpenStatus(hIMC: WinNT.HANDLE): Boolean

    /**
     * 获取输入法状态: [IME_ConversionModeValues](https://learn.microsoft.com/en-us/windows/win32/intl/ime-conversion-mode-values)
     */
    fun ImmGetConversionStatus(
        hIMC: WinNT.HANDLE,
        lpfdwConversion: IntByReference,
        lpfdwSentence: IntByReference
    ): Boolean

    companion object {
        val INSTANCE: Imm32 = Native.load("Imm32", Imm32::class.java)
        const val IME_CMODE_NATIVE = 0x0001
    }
}