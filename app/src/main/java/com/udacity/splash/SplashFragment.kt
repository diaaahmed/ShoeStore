package com.udacity.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.udacity.datastore.DataStoreManager
import com.udacity.shoestore.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private val ui by lazy {
        FragmentSplashBinding.inflate(layoutInflater)
    }

    lateinit var dataStoreManager: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataStoreManager = DataStoreManager(requireActivity())

        dataStoreManager.user_email.asLiveData().observe(requireActivity()){mail->

            if(mail.toString() =="")
            {
                findNavController().navigate(SplashFragmentDirections.actionSpachFragmentToLoginFragment())
            }
            else
            {
                findNavController()
                    .navigate(SplashFragmentDirections.actionSpachFragmentToShoeList())

            }
        }
    }
}