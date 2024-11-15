package com.pascal.templateprojectcompose.domain.repository.local

import com.pascal.templateprojectcompose.data.local.LocalDataSource
import com.pascal.templateprojectcompose.data.local.model.HistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class LocalRepository(
    private val localDataSource: LocalDataSource
): ILocalRepository {

    override suspend fun addHistory(item: HistoryEntity): Flow<Unit> {
        return flowOf(localDataSource.insertHistoryItem(item))
    }
}