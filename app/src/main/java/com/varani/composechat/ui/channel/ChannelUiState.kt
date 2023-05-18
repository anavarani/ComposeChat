package com.varani.composechat.ui.channel

import androidx.compose.runtime.toMutableStateList

class ChannelUiState(
    channels: List<Channel>,
    val onNewChannelCreated: (Channel) -> Unit
) {

    private val _channels: MutableList<Channel> = channels.toMutableStateList()
    val channels: List<Channel> = _channels

    fun addNewChannel(name: String) {
        val newChannel = Channel(name = name)
        _channels.add(newChannel)
        onNewChannelCreated(newChannel)
    }
}