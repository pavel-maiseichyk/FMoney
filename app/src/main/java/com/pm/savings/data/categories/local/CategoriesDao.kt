package com.pm.savings.data.categories.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM category")
    fun getCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Long): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity)

    @Query("DELETE FROM category WHERE id = :id")
    suspend fun deleteCategoryById(id: Long)

    @Transaction
    @Query("SELECT * FROM category")
    fun getCategoriesWithOperations(): Flow<List<CategoryWithOperationsEntity>>
}