package com.codingwithmitch.mvvmrecipeapp.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codingwithmitch.mvvmrecipeapp.R
import com.codingwithmitch.mvvmrecipeapp.domain.model.Recipe
import com.codingwithmitch.mvvmrecipeapp.presentation.BaseApplication
import com.codingwithmitch.mvvmrecipeapp.presentation.components.AnimatedHeartButton
import com.codingwithmitch.mvvmrecipeapp.presentation.components.HeartButtonState
import com.codingwithmitch.mvvmrecipeapp.presentation.components.LoadingRecipeShimmer
import com.codingwithmitch.mvvmrecipeapp.util.DEFAULT_RECIPE_IMAGE
import com.codingwithmitch.mvvmrecipeapp.util.loadPicture
import com.codingwithmitch.openchat.common.framework.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

const val IMAGE_HEIGHT = 260

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.compose_view, container, false
        ).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {

                val displayProgressBar by viewModel.loading.collectAsState()

                val recipe by viewModel.recipe.collectAsState()

                val isFavorite by viewModel.isFavorite.collectAsState()

                AppTheme(
                    darkTheme = !application.isLight,
                    progressBarIsDisplayed = displayProgressBar,
                ){
                    if (displayProgressBar && recipe == null) LoadingRecipeShimmer(IMAGE_HEIGHT)
                    else recipe?.let {
                        RecipeView(
                                recipe = it,
                                isFavorite = isFavorite,
                                onToggleFavorite = viewModel::onToggleFavorite,
                        )
                    }
//                    LoadingRecipeShimmer(IMAGE_HEIGHT)
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun RecipeView(
        recipe: Recipe,
        isFavorite: Boolean,
        onToggleFavorite: () -> Unit,
){
    ScrollableColumn(
            modifier = Modifier
                    .fillMaxWidth()
    ) {
        recipe.featuredImage?.let { url ->
            val image by loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).collectAsState()
            image?.let { img ->
                Image(
                        asset = img.asImageAsset(),
                        modifier = Modifier
                                .fillMaxWidth()
                                .preferredHeight(IMAGE_HEIGHT.dp)
                        ,
                        contentScale = ContentScale.Crop,
                )
            }
            Column(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
            ) {
                recipe.publisher?.let { publisher ->
                    Row(
                            modifier = Modifier
                                    .height(40.dp)
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                    ){
                        AnimatedHeartButton(
                                modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                ,
                                buttonState = if(isFavorite) mutableStateOf(HeartButtonState.ACTIVE) else mutableStateOf(HeartButtonState.IDLE),
                                onToggle = onToggleFavorite,
                                iconSize = 24.dp,
                                expandIconSize = 36.dp
                        )
                        Spacer(modifier = Modifier.padding(end = 15.dp))
                        val updated = recipe.dateUpdated
                        Text(
                                text = if(updated != null) {
                                    "Updated ${updated} by ${publisher}"
                                }
                                else {
                                    "By ${publisher}"
                                }
                                ,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.CenterVertically)
                                ,
                                style = MaterialTheme.typography.caption
                        )
                    }

                }
                recipe.title?.let { title ->
                    Row(
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp)
                    ){
                        Text(
                                text = title,
                                modifier = Modifier
                                        .fillMaxWidth(0.85f)
                                        .wrapContentWidth(Alignment.Start)
                                ,
                                style = MaterialTheme.typography.h3
                        )
                        val rank = recipe.rating.toString()
                        Text(
                                text = rank,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(Alignment.End)
                                        .align(Alignment.CenterVertically)
                                ,
                                style = MaterialTheme.typography.h5
                        )
                    }
                }
                recipe.description?.let { description ->
                    if(description != "N/A"){
                        Text(
                                text = description,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp)
                                ,
                                style = MaterialTheme.typography.body1
                        )
                    }
                }
                recipe.ingredients?.let { ingredients ->
                    for(ingredient in ingredients){
                        Text(
                                text = ingredient,
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 4.dp)
                                ,
                                style = MaterialTheme.typography.body1
                        )
                    }
                }
                recipe.cookingInstructions?.let { instructions ->
                    Text(
                            text = instructions,
                            modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp)
                            ,
                            style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}




















