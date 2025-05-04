package com.stsd.selftaughtsoftwaredevelopers.shared.di.module

//@Module
//@InstallIn(SingletonComponent::class)
//object StorageModule {
//
//    private const val SUDOKU_HERO_PREFERENCES = "sudoku_hero_application_settings"
//
//    @Singleton
//    @Provides
//    fun providePreferencesDataStore(
//        @ApplicationContext context: Context
//    ): DataStore<Preferences> {
//        return PreferenceDataStoreFactory.create(
//            corruptionHandler = ReplaceFileCorruptionHandler(
//                produceNewData = { emptyPreferences() }
//            ),
//            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
//            produceFile = { context.preferencesDataStoreFile(SUDOKU_HERO_PREFERENCES) }
//        )
//    }
//}
