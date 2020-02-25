package com.apdallahy3.basearch.data.response


import android.service.autofill.FieldClassification
 import com.squareup.moshi.Json

/*
 * Created by Rifqi Mulya Fahmi on 01/12/18.
 */

data class MatchesResponse(
    @Json(name = "events")
    val events: List<FieldClassification.Match?>?
)