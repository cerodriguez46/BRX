package christopher.brx

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//ViewModelFactory is
class DetailViewModelFactory(
    private val storesProperty: StoresPropertyX,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(storesProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}