package com.varani.composechat.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varani.composechat.data.local.entities.ChannelEntity.Companion.CHANNEL_TABLE
import com.varani.composechat.ui.channel.Channel
import java.time.LocalDateTime
import java.util.UUID

/**
 * Created by Ana Varani on 11/05/2023.
 */
@Entity(tableName = CHANNEL_TABLE)
data class ChannelEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = CHANNEL_ID)
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = CHANNEL_NAME)
    val name: String,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        const val CHANNEL_TABLE = "channel"
        const val CHANNEL_ID = "id"
        const val CHANNEL_NAME = "channel_name"
        const val CREATED_AT = "created_at"
    }
}

fun ChannelEntity.toExternalModel() = Channel(id = id, name = this.name)

fun List<ChannelEntity>.toExternalModel() = map(ChannelEntity::toExternalModel)

fun Channel.toEntity() = ChannelEntity(id = id, name = name)