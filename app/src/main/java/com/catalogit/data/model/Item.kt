package com.catalogit.data.model

/**
 * Created by gabeira@gmail.com on 10/12/18.
 */
data class Item(
    val title: String,
    val year: Int,
    val description: String,
    val images: Images
)

data class Images(
    val portrait: String,
    val landscape: String
)