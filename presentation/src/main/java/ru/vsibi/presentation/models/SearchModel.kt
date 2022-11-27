package ru.vsibi.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchModel : Parcelable {
    var date : String? = null
    var personsDesc : String? = null
    var country : String = ""
}