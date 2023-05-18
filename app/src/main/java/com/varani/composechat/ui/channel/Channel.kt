package com.varani.composechat.ui.channel

import java.util.UUID

data class Channel(
    val id: UUID = UUID.randomUUID(),
    val name: String
)