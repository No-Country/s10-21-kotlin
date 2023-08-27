package com.example.nocountrydonation.feature_donors.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
@Parcelize
data class Donors(
    var name : String = "",
    var city : String = "",
    var blood : String = "",
    var image : String = "",
    var hospital : String = "",
    var created_at : Date? = null
): Parcelable
