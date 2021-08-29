package com.core.dto.login

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by aMiir on 6/3/20
 * Drunk, Fix Later
 */

@Parcelize
data class ProjectDto(

    @Expose
    @Keep
    var ProjectID : Int,

    @Expose
    @Keep
    var ProjectCode : String,

    @Expose
    @Keep
    var ProjectTitle : String

):Parcelable