package com.pm.savings.data.categories.repository

import com.pm.savings.data.categories.mapper.toCategory
import com.pm.savings.data.categories.mapper.toCategoryWithOperations
import com.pm.savings.data.categories.mapper.toEntity
import com.pm.savings.data.core.local.FinanceDatabase
import com.pm.savings.domain.category.model.Category
import com.pm.savings.domain.category.model.CategoryWithOperations
import com.pm.savings.domain.category.repository.CategoryDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomCategoryDataSource(
    db: FinanceDatabase
) : CategoryDataSource {

    private val dao = db.categoriesDao()

    override fun getCategories(): Flow<List<Category>> {
        return dao.getCategories().map { list -> list.map { it.toCategory() } }
    }

    override suspend fun getCategoryById(id: Long): Category {
        return dao.getCategoryById(id).toCategory()
    }

    override suspend fun insertCategory(category: Category) {
        dao.insertCategory(category.toEntity())
    }

    override suspend fun deleteCategoryById(id: Long) {
        dao.deleteCategoryById(id)
    }

    override fun getStatsWithOperations(): Flow<List<CategoryWithOperations>> {
        return dao.getCategoriesWithOperations().map { list ->
            list.map { it.toCategoryWithOperations() }
        }
    }
}