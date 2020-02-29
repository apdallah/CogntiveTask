package com.apdallahy3.basearch.data.source.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "places_tabel")
data class PlacesEntitiy(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    var id: String,
    var name: String? = null,
    var address: String? = null,
    var thumbinal: String? = null


)