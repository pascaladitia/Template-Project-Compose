import androidx.room.Room
import com.pascal.templateprojectcompose.data.local.LocalDataSource
import com.pascal.templateprojectcompose.data.local.database.AppDatabase
import com.pascal.templateprojectcompose.data.local.database.DatabaseConstants
import com.pascal.templateprojectcompose.domain.repository.local.ILocalRepository
import com.pascal.templateprojectcompose.domain.repository.local.LocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localModule = module {

    single(named(DatabaseConstants.DATABASE_NAME)) {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, DatabaseConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single(named("LOCAL_DATA_SOURCE")) { LocalDataSource(get(named(DatabaseConstants.DATABASE_NAME))) }

    single<ILocalRepository> { LocalRepository(get(named("LOCAL_DATA_SOURCE"))) }

}