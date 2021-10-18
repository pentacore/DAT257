package dat257.gyro.data.local

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

// https://svvashishtha.medium.com/using-room-with-hilt-cb57a1bc32f
@InstallIn(SingletonComponent::class)
@Module
class AbstractDBModule {
    @Provides
    fun provideSettingsDao(db: AbstractDb) = db.settingsDao()

    @Provides
    @Singleton
    fun provideAbstractDb(@ApplicationContext c: Context) = AbstractDb.getDatabase(
        c,
        CoroutineScope(SupervisorJob())
    )
}