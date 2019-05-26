package christopher.brx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

//Class DetailViewModel that has a constructor of params storesProperty that inherits from StoresPropertyX and
// inherits from AndroidViewModel
class DetailViewModel(storesProperty: StoresPropertyX, app: Application) : AndroidViewModel(app) {

//private value selectedProperty is a MutableLiveData of StoresPropertyX
    private val _selectedProperty = MutableLiveData<StoresPropertyX>()

//kotlin auto makes getters and setters for your fields. You can override this with get and backing property
    //selectedProperty is a backing property that overrides auto generated getter for _selectedProperty which is a
    //MutableLiveData of StoresPropetyX
    //property is a private field that contains getters and setters
    //if we encounter a case that is not covered by functionality of the backing fields feature and we want to access the
// field directly without usage of the getter or setter we should use the backing properties feature, can access directly
    val selectedProperty: LiveData<StoresPropertyX>
        get() = _selectedProperty

    //init block basically promises the compiler that these variables will be initialized later before being used
    init {
        _selectedProperty.value = storesProperty
    }
}