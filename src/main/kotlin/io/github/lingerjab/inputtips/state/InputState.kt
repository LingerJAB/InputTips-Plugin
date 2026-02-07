package io.github.lingerjab.inputtips.state

import javax.swing.Icon

enum class InputState(
    val displayText: String,
    val statusName: String,
    val displayIcon: Icon
){
    EN("E","英文", StateIcons.EN),
    CAPS("A","大写锁定", StateIcons.CAPS),
    CN("中","中文", StateIcons.CN)
}