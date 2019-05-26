package christopher.brx

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StoreDao {

    @Query("select * from databasestores")
    fun getStoreDetails(): LiveData<List<DatabaseStores>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg stores: DatabaseStores)
}
    @Database(entities = [DatabaseStores::class], version = 1)
    abstract class StoreDatabase : RoomDatabase() {

        abstract val storeDao: StoreDao
    }


        private lateinit var INSTANCE: StoreDatabase

        fun getDatabase(context: Context): StoreDatabase {
            synchronized(StoreDatabase::class.java) {

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    "stores"
                ).build()
            }
        }
        return INSTANCE

    }

