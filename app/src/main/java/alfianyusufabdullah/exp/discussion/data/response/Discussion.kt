package alfianyusufabdullah.exp.discussion.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Discussion(
    val createdAt: Long? = null,
    val name: String? = null,
    val comment: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val reply: Int? = null,
    val parentId: Int? = null
) : Parcelable