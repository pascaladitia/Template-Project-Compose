package com.pascal.templateprojectcompose.data.local

import com.pascal.templateprojectcompose.data.local.model.HistoryEntity
import com.pascal.templateprojectcompose.data.local.database.AppDatabase

class LocalDataSource(private val database: AppDatabase) {

    suspend fun insertHistoryItem(item: HistoryEntity) {
        database.historyDao().insertHistory(item)
    }

}