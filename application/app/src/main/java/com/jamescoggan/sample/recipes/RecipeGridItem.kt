package com.jamescoggan.sample.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jamescoggan.sample.R
import com.jamescoggan.sample.ui.models.RecipeCategory
import com.jamescoggan.sample.ui.models.RecipeUiItem
import com.jamescoggan.sample.ui.models.label
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme

@Composable
fun RecipeListGridItem(
    recipeUiItem: RecipeUiItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
    ) {
        Column(modifier = Modifier.clickable(onClick = onClick)) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = recipeUiItem.imageUrl,
                contentDescription = recipeUiItem.title,
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.recipe_placeholder)
            )
            Column(Modifier.padding(8.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                Text(text = recipeUiItem.title, style = MaterialTheme.typography.labelLarge)


                Text(
                    text = recipeUiItem.category.label(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
@Preview
fun RecipeListGridItemPreview() {
    AndroidAppSampleTheme {
        Row(
            modifier = Modifier
                .height(350.dp)
                .width(200.dp)
        ) {
            RecipeListGridItem(
                RecipeUiItem(
                    id = 0,
                    title = "Chicken chow mei",
                    imageUrl = "http://google.com",
                    category = RecipeCategory.CHINESE
                ),
            )
        }
    }
}