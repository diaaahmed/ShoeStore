package com.udacity.shoelist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.datastore.DataStoreManager
import com.udacity.model.Shoe

import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.viewmodel.ShoeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoeList : Fragment() {

    private val ui by lazy {
        FragmentShoeListBinding.inflate(layoutInflater)
    }
    private lateinit var dataStoreManager: DataStoreManager

    private lateinit var shoeViewModel: ShoeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireActivity())

        shoeViewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        shoeViewModel.shoeList.observe(requireActivity(), Observer {

            for(shoe in shoeViewModel.shoeList.value!!)
            {
                val loop_view: ItemShoeBinding = ItemShoeBinding.inflate(layoutInflater)
                loop_view.shoe = shoe
                ui.lnList.addView(loop_view.root)

            }
        })

        ui.toDetails.setOnClickListener {
            findNavController().navigate(ShoeListDirections.actionShoeListToShoeDetailsFragment())
        }

        requireActivity().addMenuProvider(object: MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater)
            {
                menuInflater.inflate(R.menu.shoe_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean
            {
                when(menuItem?.itemId)
                {

                    R.id.log_out -> {
                        Toast.makeText(requireActivity(),
                            "Logout", Toast.LENGTH_SHORT).show()
                        GlobalScope.launch { dataStoreManager.clearDataStore()
                        }
                        findNavController().navigate(ShoeListDirections.actionShoeListToSplashFragment())
                    }

                }

                return false
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

//    fun addToList(shoe:Shoe)
//    {
//        val loop_view: ItemShoeBinding = ItemShoeBinding.inflate(layoutInflater)
//
//        loop_view.shoe = shoe
//        ui.lnList.addView(loop_view.root)
//
//    }

}