package com.example.myapplicationjetpack.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tablefavMovie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String? = null,

    @ColumnInfo(name = "movieTitle")
    var title: String? = null,

    @ColumnInfo(name = "movieDesc")
    var desc: String? = null,

    @ColumnInfo(name = "movieDate")
    var date: String? = null,

    @ColumnInfo(name = "moviePoster")
    var poster: String? = null,

    @NonNull
    @ColumnInfo(name = "favState")
    var favState: Boolean = false
): Parcelable