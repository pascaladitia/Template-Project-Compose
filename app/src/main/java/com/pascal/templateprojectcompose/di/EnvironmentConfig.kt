package com.pascal.templateprojectcompose.di

import com.pascal.templateprojectcompose.BuildConfig


object EnvironmentConfig {
    const val BASE_DOMAIN = BuildConfig.BASE_URL
    const val BASE_URL = "https://$BASE_DOMAIN/"
    val allowedSSlFingerprints = emptyList<String>()
}