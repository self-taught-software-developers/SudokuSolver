package com.stsd.selftaughtsoftwaredevelopers.shared.di

import com.stsd.selftaughtsoftwaredevelopers.shared.di.module.StateModule
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module
import org.koin.ksp.generated.module

fun createKoinConfiguration() : KoinConfiguration {
    return KoinConfiguration {
        modules(sharedModule)
    }
}

val sharedModule get() = module {
    includes(
//        daoModule,
//        databaseModule,
        StateModule().module,
//        DomainModule().module,
//        RepositoryModule().module
    )
}