package com.codingwithmitch.mvvmrecipeapp.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codingwithmitch.mvvmrecipeapp.presentation.components.PulsingDemo
import com.codingwithmitch.mvvmrecipeapp.presentation.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipes = viewModel.recipes.value

                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val categoryScrollPosition = viewModel.categoryScrollPosition

                val loading = viewModel.loading.value

                Column {

                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::newSearch,
                        categories = getAllFoodCategories(),
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                        scrollPosition = categoryScrollPosition,
                        onChangeScrollPosition = viewModel::onChangeCategoryScrollPosition,
                    )

                    PulsingDemo()


//                    Box(modifier = Modifier.fillMaxSize()) {
//                        LazyColumn {
//                            itemsIndexed(
//                                items = recipes
//                            ) { index, recipe ->
//                                RecipeCard(recipe = recipe, onClick = {})
//                            }
//                        }
//                        CircularIndeterminateProgressBar(isDisplayed = loading, verticalBias = 0.3f)
//                    }

                }
            }
        }
    }

}























