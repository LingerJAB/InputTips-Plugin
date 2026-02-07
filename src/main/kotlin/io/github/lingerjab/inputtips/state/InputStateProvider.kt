package io.github.lingerjab.inputtips.state

interface InputStateProvider {
    fun getCurrentState(): InputState
}