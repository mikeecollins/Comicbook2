package ie.setu.assignment2.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ComicbookModel(var id: Long = 0,
                          var title: String = "",
                          var author: String = "",
                          var chapter: String = "") : Parcelable

