package com.pascal.templateprojectcompose.domain.usecase

import com.pascal.templateprojectcompose.domain.repository.local.ILocalRepository

class LocalUC (private val repository: ILocalRepository) {
    suspend fun getHistory() {

    }
}