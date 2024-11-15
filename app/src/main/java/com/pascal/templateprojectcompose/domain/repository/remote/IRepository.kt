package com.pascal.templateprojectcompose.domain.repository.remote

import com.pascal.templateprojectcompose.data.remote.dtos.ResponseHistory
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IRepository {

    suspend fun history(): Flow<Response<ResponseHistory?>>
}