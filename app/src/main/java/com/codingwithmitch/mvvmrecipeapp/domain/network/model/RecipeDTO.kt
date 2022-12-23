package com.codingwithmitch.mvvmrecipeapp.domain.network.model

import com.google.gson.annotations.SerializedName

class RecipeDTO(
    @SerializedName("pk")
    var pk: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("rating")
    var rating: Int? = null,
    @SerializedName("publisher")
    var publisher: String? = null,
    @SerializedName("source_url")
    var sourceUrl: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("cooking_instructions")
    var cookingInstructions: String? = null,
    @SerializedName("date_added")
    var date_added: String? = null,
    @SerializedName("date_updated")
    var date_updated: String? = null,
    @SerializedName("featured_image")
    var featuredImage: String? = null,
    @SerializedName("ingredients")
    var ingredients: List<String>? = null,
) {

}