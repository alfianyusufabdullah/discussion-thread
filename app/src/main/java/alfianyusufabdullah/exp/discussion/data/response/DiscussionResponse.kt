package alfianyusufabdullah.exp.discussion.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DiscussionResponse(
	val discussion: List<Discussion> = emptyList(),
	val status: String? = null
) : Parcelable


