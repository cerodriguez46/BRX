package christopher.brx

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import christopher.brx.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    /**
     * Lazily initialize our [OverviewViewModel].
     */
    //Overview view model is instantiated here once, takes reference with this to refer to fragment, and calls the exact class
    private val viewModel: OverviewViewModel by lazy {

        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */

    //? will show null if container ViewGroup, savedInstanceStateBundle, or View is null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //calls inflate from data binding class FragmentOverviewBinding and sets to binding variable
        //replaces set contentview within binding activity that connects everything
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

//Tells recycler view to use adapter we just made and tell the adapter what data it should be adapting
        binding.storesGrid.adapter= StoreGridAdapter(StoreGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
//navigation, if StoresPropertyX is not null, then navigate with action to detail, sets up LiveData navigate to selected property
        //observer to watch the lifecycle of this fragmnent
        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( null != it ) {
                this.findNavController().navigate(OverviewFragmentDirections.actionShowDetail(it))
                //call display properties complete method from OverviewViewModel that does not display a value
                viewModel.displayPropertyDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * Inflates the overflow menu that contains filtering options.
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}