package christopher.brx

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoresRepository(private val database: StoreDatabase) {

    val stores: LiveData<List<StoresPropertyX>> = Transformations.map(database.storeDao.getStoreDetails()) {
        it.asDomainModel()
    }


    suspend fun refreshStores() {
        withContext(Dispatchers.IO){

            val storeList = StoresApi.retrofitService.getProperties().await()

            database.storeDao.insertAll(*storeList.asDatabaseModel())
        }
    }
}