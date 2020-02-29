package com.apdallahy3.basearch.data.response

class PhotoResponse() {
    val response: PhotoResponseItem? = null
}

data class PhotoResponseItem(
    val photos: PhotoItem? = null
)

data class PhotoItem(
    val items:List<Item>?=null
)
data class Item(
    val id:String?=null,
    val prefix:String?=null,
    val suffix:String?=null,
    val width:String?=null,
    val height:String?=null
)