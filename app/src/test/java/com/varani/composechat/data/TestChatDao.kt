package com.varani.composechat.data

import com.varani.composechat.data.local.dao.ChatDao
import com.varani.composechat.data.local.entities.ChatEntity

/**
 * Created by Ana Varani on 11/05/2023.
 */
class TestChatDao : ChatDao {
    override suspend fun insert(chat: ChatEntity) {}
}