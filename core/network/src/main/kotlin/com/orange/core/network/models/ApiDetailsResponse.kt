package com.orange.core.network.models

import com.orange.core.domain.models.Program
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

fun ApiDetailsResponse.toProgramDetailed(program: Program): Program {
    return (contents.let { content ->
        val pitch = content.pitch
        program.copy(
            pitch = pitch,
        )
    })
}


@Serializable
data class ApiDetailsResponse(
    val template: String,
    val parentalrating: Int,
    val categoryID: Int,
    val contents: Contents,
)

@Serializable
data class Contents(
    val parentalrating: Int,
    val isbookmarkable: Boolean,
    val isdownloadable: Boolean,
    val number: String? = null,
    val title: List<ContentItem>,
    val duration: String,
    val availability: List<ContentItem>,
    val summary: String,
    val highlefticon: String? = null,
    val highrighticon: String? = null,
    val subtitle: String? = null,
    val subtitlefocus: String? = null,
    val pitch: String,
    val bannerinfo: List<ContentItem>,
    val description: List<List<String>>,
    val imageurl: String,
    val fullscreenimageurl: String,
    val linearplanning: List<String>,
    val acontents: String? = null,
    val playinfoid: PlayInfoId,
    val playinfo: PlayInfo,
    val id: String,
    val zonesinfo: ZonesInfo,
)

@Serializable
data class ContentItem(
    val type: String,
    val value: String,
    val color: String? = null,
)

@Serializable
data class PlayInfoId(
    @SerialName("hd") val hd: String?,
    @SerialName("sd") val sd: String?,
    @SerialName("uhd") val uhd: String?,
)

@Serializable
data class PlayInfo(
    val tokenurl: String? = null,
    val url: String? = null,
)

@kotlinx.serialization.Serializable
data class ZonesInfo(
    val duration: Int,
    val endcreditsautocompleted: Boolean,
    val previouslytcin: String? = null,
    val previouslytcout: String? = null,
    val startcreditstcin: Int,
    val startcreditstcout: Int,
    val endcreditstcin: Int,
    val endcreditstcout: Int,
)
