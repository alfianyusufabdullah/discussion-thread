package alfianyusufabdullah.exp.discussion.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Discussion(
    val createdAt: Long? = 0,
    val name: String? = "-",
    val comment: String? = "-",
    val id: String? = "-",
    val title: String? = "-",
    val reply: Int? = 0,
    val parentId: String? = "-"
) : Parcelable