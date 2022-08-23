package com.udacity.shoedetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.model.Shoe

import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.viewmodel.ShoeViewModel
import com.udacity.viewmodel.Stata


class ShoeDetailsFragment : Fragment() {

    private val ui by lazy {
        FragmentShoeDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var shoeViewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Called view model", "View Model Called")

        shoeViewModel = ViewModelProvider(requireActivity())
            .get(ShoeViewModel::class.java)

        ui.shoeViewModel = shoeViewModel

        ui.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        ui.btnAdd.setOnClickListener {

            val shoeName = ui.edShoeName.text.toString()
            val shoeCompany = ui.edShoeCompany.text.toString()
            val shoeSize = ui.edShoeSize.text.toString()
            val shoeDescription = ui.edShoeDescription.text.toString()

            if(shoeName.isBlank())
            {
             ui.edShoeName.error = "Shoe name is required, please insert name"
            }
            if(shoeCompany.isBlank())
            {
                ui.edShoeCompany.error = "Shoe company is required, please insert name"

            }
            if(shoeSize.isBlank())
            {
                ui.edShoeSize.error = "Shoe size is required, please insert name"

            }
            if(shoeDescription.isBlank())
            {
                ui.edShoeDescription.error = "Shoe descriptions is required, please insert name"

            }
            else
            {
                val shoe = Shoe(shoeName,shoeCompany,shoeSize,shoeDescription)
                shoeViewModel.onSave(shoe)

                findNavController().navigateUp()
            }
        }

        shoeViewModel.state.observe(requireActivity(), { state->
            when(state)
            {
                Stata.SAVE -> {
                    shoeViewModel.onSaveComplete()
                }
                else -> {}
            }
        })
    }

}