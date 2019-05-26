package christopher.brx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import christopher.brx.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        //binding variable with binding generated class
        val binding = FragmentDetailBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // !! - arguments from Bundle will throw null pointer exception if it is null
        val storesProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = DetailViewModelFactory(storesProperty, application)

        //creates first instance of view model, takes this to reference detail framgnet and calls viewmodel class
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }
}