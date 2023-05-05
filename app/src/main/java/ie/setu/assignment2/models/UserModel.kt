package ie.setu.assignment2.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(  var comicbookusername: String = "",
                       var comicbookpassword: String = "") : Parcelable


