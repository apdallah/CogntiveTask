package com.apdallahy3.basearch.data.response

/**/
class NearbyPlacesResponse() {
    val response: Response? = null
}

data class Response(
    val headerLocation: String? = "-",
    val headerFullLocation: String? = "-",
    val groups: List<GroupResponse>? = null
)

data class GroupResponse(
    val type: String? = null,
    val name: String? = null,
    val items: List<GroupItemResponse>? = null
)

data class GroupItemResponse(
    val venue: Venue? = null
)

data class Venue(
    val id: String? = null,
    val name: String? = "-",
    val location: VenueLocation? = null,
    val categories: List<VenueGategories>? = null

)

data class VenueLocation(
    val address: String? = "-",
    val crossStreet: String? = "-",
    val city: String? = "-",
    val state: String? = "-",
    val country: String? = "-"
)

data class VenueGategories(
    val id: String? = null,
    val name: String? = "-"
)