package com.pascal.templateprojectcompose.di

import com.pascal.templateprojectcompose.domain.usecase.HomeUC
import com.pascal.templateprojectcompose.domain.usecase.LocalUC
import org.koin.dsl.module

val domainModule = module {
    factory { HomeUC(get()) }
    factory { LocalUC(get()) }
}
