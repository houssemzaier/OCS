package com.orange.core.network.models

import com.orange.core.domain.models.Program
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiSearchResponse(
    @SerialName("template") val template: String,
    @SerialName("parentalrating") val parentalRating: Int,
    @SerialName("title") val title: String,
    @SerialName("offset") val offset: Int,
    @SerialName("limit") val limit: String,
    @SerialName("next") val next: String?,
    @SerialName("previous") val previous: String?,
    @SerialName("total") val total: Int,
    @SerialName("count") val count: Int,
    @SerialName("filter") val filter: String?,
    @SerialName("sort") val sort: String?,
    @SerialName("contents") val contents: List<Content>?,
) {
    companion object {

        fun ApiSearchResponse.toProgramList(): List<Program> {
            return contents?.map { content ->
                val title = content.title.firstOrNull()?.value ?: ""
                val subtitle = content.subtitle
                val pitch = ""
                val thumbnailUrl = "https://statics.ocs.fr${content.imageUrl}"
                val imageUrl = "https://statics.ocs.fr${content.fullscreenImageUrl}"
                val detailLink = DetailLinkParser.parse(content.detailLink)
                val id = content.id

                Program(
                    id = id,
                    title = title,
                    subtitle = subtitle,
                    pitch = pitch,
                    thumbnailUrl = thumbnailUrl,
                    imageUrl = imageUrl,
                    detailLink = detailLink,
                )
            } ?: emptyList()
        }
    }
}


@Serializable
data class Content(
    @SerialName("title") val title: List<Title>,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("subtitlefocus") val subtitleFocus: List<String>,
    @SerialName("highlefticon") val highLeftIcon: String?,
    @SerialName("highrighticon") val highRightIcon: List<String>?,
    @SerialName("lowrightinfo") val lowRightInfo: String?,
    @SerialName("imageurl") val imageUrl: String,
    @SerialName("fullscreenimageurl") val fullscreenImageUrl: String,
    @SerialName("id") val id: String,
    @SerialName("detaillink") val detailLink: String,
    @SerialName("duration") val duration: String,
    @SerialName("playinfoid") val playInfoId: PlayInfoId,
)


@Serializable
data class Title(
    @SerialName("color") val color: String?,
    @SerialName("type") val type: String,
    @SerialName("value") val value: String,
)
