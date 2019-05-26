package christopher.brx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class StoresApiStatus { LOADING, ERROR, DONE }

//Overview view model inherits from ViewModel, use ctrl + U to view superclass that it inherits from
class OverviewViewModel : ViewModel() {



    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<StoresApiStatus>()

    // The external immutable LiveData for the request status String
    //property status with getters and setters we want to access directly with get()
    val status: LiveData<StoresApiStatus>
        get() = _status


    //Mutable live data List that deals with StoresPropertyX properties
    private val _properties = MutableLiveData<List<StoresPropertyX>>()
    //property properties with getters and setters we want to access directly with get()
    val properties: LiveData<List<StoresPropertyX>>
        get() = _properties

    //Mutable LiveData for the navigateToSelectedProperty from StoresPropertyX
    private val _navigateToSelectedProperty = MutableLiveData<StoresPropertyX>()
    //property navigateToSelectedProperty with getters and setters we want to access directly with get()
    val navigateToSelectedProperty: LiveData<StoresPropertyX>
        get() = _navigateToSelectedProperty


    //coroutine background job that can be cancelled
    private var viewModelJob = Job()
    //scope keeps track of coroutines and defines the context in which the coroutine runs
    //dispatcher sends off coroutines to run on specific thread
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


   // private val database = getDatabase()
   // private val storesRepository = StoresRepository(database)

    /*init {
        coroutineScope.launch {
            storesRepository.refreshStores()
        }
    }*/

    //val storeList = storesRepository.stores


    /**
     * Call getStoreProperties() on init so we can display status immediately.
     */
     //init block promises we'll instantiate this method later, not at creation time, saves memory by only calling this when needed
    init {
        getStoreProperties()
    }



    // private function called getStoreProperties that has no return value
    private fun getStoreProperties() {

        //Coroutine is asynchronous, non blocking, and use suspend functions to make code sequential
        coroutineScope.launch {
            var getPropertiesDeferred = StoresApi.retrofitService.getProperties()
            try {
                _status.value = StoresApiStatus.LOADING
                // Await the completion of our Retrofit request
                val listResult = getPropertiesDeferred.await()
                _status.value = StoresApiStatus.DONE
                    _properties.value = listResult.stores
            } catch (e: Exception) {
                _status.value = StoresApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }
    /* // var storeList: List<StoresPropertyX>
        StoresApi.retrofitService.getProperties().enqueue(object: retrofit2.Callback<StoresProperty>{
            override fun onFailure(call: Call<StoresProperty>, t: Throwable) {
                _status.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<StoresProperty>, response: Response<StoresProperty>) {

                _status.value = "Success: ${response.body()!!.stores.size} Stores properties retrieved"
            }


        })
        _status.value = "Set the Stores API Response here!"
    }*/


    override fun onCleared() {
        //super means calling a method from a class it inherits from
        super.onCleared()
//cancels coroutine job that is running in the background
        viewModelJob.cancel()
    }

    // function called displayPropertyDetails that takes a storesProperty param from StoresPropertyX
    fun displayPropertyDetails(storesProperty: StoresPropertyX) {
        _navigateToSelectedProperty.value = storesProperty
    }

    //function called displayPropertyDetailsComplete that does not return a value
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    /*class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }*/
}