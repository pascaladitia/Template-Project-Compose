package com.pascal.templateprojectcompose.domain.repository.local
import com.pascal.templateprojectcompose.data.local.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    suspend fun addHistory(item: HistoryEntity): Flow<Unit>
}