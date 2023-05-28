package com.pm.savings.domain.category.repository

import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.category.model.CategoryWithOperations
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    fun getCategories(): Flow<List<Category>>
    suspend fun getCategoryById(id: Long): Category
    suspend fun insertCategory(category: Category)
    suspend fun deleteCategoryById(id: Long)

    fun getStatsWithOperations(): Flow<List<CategoryWithOperations>>
}