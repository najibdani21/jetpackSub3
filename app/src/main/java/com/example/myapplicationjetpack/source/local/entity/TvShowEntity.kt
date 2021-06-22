package com.example.myapplicationjetpack.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tablefavTvShow")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: String? = null,

    @ColumnInfo(name = "tvShowTitle")
    var title: String? = null,

    @ColumnInfo(name = "tvShowDesc")
    var desc: String? = null,

    @ColumnInfo(name = "tvShowDate")
    var date: String? = null,

    @ColumnInfo(name = "tvShowPoster")
    var poster: String? = null,

    @NonNull
    @ColumnInfo(name = "favState")
    var favState: Boolean = false
): Parcelable