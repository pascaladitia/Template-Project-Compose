package com.pascal.templateprojectcompose.di

import com.pascal.templateprojectcompose.data.setup.AppServiceFactory
import com.pascal.templateprojectcompose.data.setup.HttpClientFactory
import com.pascal.templateprojectcompose.data.setup.ServiceFactory
import com.pascal.templateprojectcompose.di.EnvironmentConfig.BASE_DOMAIN
import com.pascal.templateprojectcompose.di.EnvironmentConfig.allowedSSlFingerprints
import com.pascal.templateprojectcompose.domain.repository.remote.IRepository
import com.pascal.templateprojectcompose.domain.repository.remote.Repository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val remoteModule = module {
    single(named("HTTP_CLIENT")) { HttpClientFactory(androidContext(), BASE_DOMAIN, allowedSSlFingerprints) }
    single(named("SERVICE_FACTORY")) { ServiceFactory(EnvironmentConfig.BASE_URL) }
    single(named("APP_SERVICE")) {
        AppServiceFactory(get(named("HTTP_CLIENT"))).getAppService(get(named("SERVICE_FACTORY")))
    }
    single<IRepository> { Repository(get(named("APP_SERVICE"))) }
}