package com.pascal.templateprojectcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pascal.templateprojectcompose.data.local.model.HistoryEntity

@Dao
abstract class HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertHistory(cachedTest: HistoryEntity) : Long

}