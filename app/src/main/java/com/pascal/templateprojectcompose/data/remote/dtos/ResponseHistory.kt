package com.pascal.templateprojectcompose.data.remote.dtos

import android.os.Parcelable
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseHistory(
	@field:SerializedName("record")
	@Embedded
	val records: String,

	@field:SerializedName("metadata")
	@Embedded
	val metadata: String
) : Parcelable