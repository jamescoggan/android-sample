package com.jamescoggan.sample.testingData

import com.jamescoggan.sample.ui.models.RecipeCategory
import com.jamescoggan.sample.ui.models.RecipeUiItem
import kotlinx.collections.immutable.persistentListOf

private const val recipeStepsEncoded =
    "PGgyPlNwYWdoZXR0aSBDYXJib25hcmEgUmVjaXBlPC9oMj4KPHA+PHN0cm9uZz5JbmdyZWRpZW50czo8L3N0cm9uZz48L3A+Cjx1bD4KICAgIDxsaT4yMDBnIFNwYWdoZXR0aTwvbGk+CiAgICA8bGk+MTAwZyBQYW5jZXR0YSBvciBHdWFuY2lhbGU8L2xpPgogICAgPGxpPjIgbGFyZ2UgZWdnczwvbGk+CiAgICA8bGk+NTBnIFBlY29yaW5vIFJvbWFubyBjaGVlc2UgKGdyYXRlZCk8L2xpPgogICAgPGxpPjUwZyBQYXJtZXNhbiBjaGVlc2UgKGdyYXRlZCk8L2xpPgogICAgPGxpPkZyZXNobHkgZ3JvdW5kIGJsYWNrIHBlcHBlcjwvbGk+CiAgICA8bGk+U2FsdDwvbGk+CjwvdWw+Cgo8cD48c3Ryb25nPkluc3RydWN0aW9uczo8L3N0cm9uZz48L3A+CjxvbD4KICAgIDxsaT5CcmluZyBhIGxhcmdlIHBvdCBvZiBzYWx0ZWQgd2F0ZXIgdG8gYSBib2lsLCB0aGVuIGNvb2sgdGhlIHNwYWdoZXR0aSBhY2NvcmRpbmcgdG8gcGFja2FnZSBpbnN0cnVjdGlvbnMgdW50aWwgYWwgZGVudGUuPC9saT4KICAgIDxsaT5JbiBhIGJvd2wsIHdoaXNrIHRvZ2V0aGVyIHRoZSBlZ2dzLCBQZWNvcmlubyBSb21hbm8sIFBhcm1lc2FuLCBhbmQgYmxhY2sgcGVwcGVyLiBTZXQgYXNpZGUuPC9saT4KICAgIDxsaT5JbiBhIHBhbiwgY29vayB0aGUgcGFuY2V0dGEgb3IgZ3VhbmNpYWxlIG92ZXIgbWVkaXVtIGhlYXQgdW50aWwgY3Jpc3B5LiBSZW1vdmUgZnJvbSBoZWF0LjwvbGk+CiAgICA8bGk+RHJhaW4gdGhlIHNwYWdoZXR0aSwgcmVzZXJ2aW5nIHNvbWUgb2YgdGhlIHBhc3RhIHdhdGVyLjwvbGk+CiAgICA8bGk+QWRkIHRoZSBzcGFnaGV0dGkgdG8gdGhlIHBhbmNldHRhIGluIHRoZSBwYW4gYW5kIHRvc3MgdG8gY29hdC48L2xpPgogICAgPGxpPlJlbW92ZSB0aGUgcGFuIGZyb20gaGVhdCBhbmQgcXVpY2tseSBzdGlyIGluIHRoZSBlZ2cgYW5kIGNoZWVzZSBtaXh0dXJlLCB1c2luZyBhIGxpdHRsZSByZXNlcnZlZCBwYXN0YSB3YXRlciB0byBjcmVhdGUgYSBjcmVhbXkgc2F1Y2UuPC9saT4KICAgIDxsaT5TZWFzb24gd2l0aCBtb3JlIGJsYWNrIHBlcHBlciwgYW5kIHNlcnZlIGltbWVkaWF0ZWx5IHdpdGggZXh0cmEgUGVjb3Jpbm8gUm9tYW5vLjwvbGk+Cjwvb2w+"


val recommendedRecipesSamples = persistentListOf(
    RecipeUiItem(
        id = 0,
        title = "Chicken chow mei",
        imageUrl = "https://www.cookingclassy.com/wp-content/uploads/2019/01/chow-mein-4.jpg",
        category = RecipeCategory.CHINESE,
        cookingSteps = recipeStepsEncoded
    ), RecipeUiItem(
        id = 1,
        title = "Spanish Paella",
        imageUrl = "https://www.jocooks.com/wp-content/uploads/2018/12/paella-1.jpg",
        category = RecipeCategory.SPANISH,
        cookingSteps = recipeStepsEncoded
    ), RecipeUiItem(
        id = 2,
        title = "Butter chicken",
        imageUrl = "https://www.simplyrecipes.com/thmb/1SXZ_F1GC6ww_ppWnrdbKgHi9fQ=/2000x1333/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__01__Butter-Chicken-LEAD-2-6ca76f24bbe74114a09958073cb9c76f.jpg",
        category = RecipeCategory.INDIAN,
        cookingSteps = recipeStepsEncoded
    )
)

