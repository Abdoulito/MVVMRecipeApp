package com.codingwithmitch.mvvmrecipeapp.domain.network.model

import com.codingwithmitch.mvvmrecipeapp.domain.model.Recipe
import com.codingwithmitch.mvvmrecipeapp.domain.util.DomainMapper

class RecipeDtoMapper: DomainMapper<RecipeDTO,Recipe> {
    override fun mapFromEntity(entity: RecipeDTO): Recipe {
        return Recipe(cookingInstructions = entity.cookingInstructions,
        date_added = entity.date_added,
        date_updated = entity.date_updated,
        description = entity.description,
        featuredImage = entity.featuredImage,
        id = entity.pk,
        ingredients = entity.ingredients?: listOf(),
            publisher = entity.publisher,
            rating = entity.rating,
            sourceUrl = entity.sourceUrl,
            title = entity.title
        )
    }

    override fun mapToEntity(domainModel: Recipe): RecipeDTO {
        return RecipeDTO(
            cookingInstructions = domainModel.cookingInstructions,
            date_added = domainModel.date_added,
            date_updated = domainModel.date_updated,
            description = domainModel.description,
            featuredImage = domainModel.featuredImage,
            pk = domainModel.id,
            ingredients = domainModel.ingredients,
            publisher = domainModel.publisher,
            rating = domainModel.rating,
            sourceUrl = domainModel.sourceUrl,
            title = domainModel.title
        )
    }

    fun fromEntityList(initial:List<RecipeDTO>):List<Recipe>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Recipe>):List<RecipeDTO>{
        return initial.map { mapToEntity(it) }
    }
}