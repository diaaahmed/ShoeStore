package com.udacity.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.datastore.DataStoreManager

import com.udacity.shoestore.databinding.FragmentLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment()
{
    private val ui by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    var email = ""
    var pass = ""

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

       observeData()

        ui.btnLogin.setOnClickListener {
            storeDate()
        }
    }

    private fun storeDate()
    {
        var email = ui.edEmail.text.toString()
        var pass = ui.edPass.text.toString()

        if(email.isBlank())
        {
            ui.edEmail.error = "Email is required"
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            ui.edEmail.error = "Email is not correct"
            return
        }
        if(pass.isBlank())
        {
            ui.edPass.error = "Password is required"
            return
        }
        else
        {
            GlobalScope.launch {
                dataStoreManager.storeUserInfo(email,pass)
            }

            moveToOnBoarding()
        }

    }

    private fun moveToOnBoarding()
    {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOnBoardingFragment())

    }

    private fun observeData()
    {
//        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShoeList())

//        dataStoreManager.user_email.asLiveData().observe(requireActivity()){
//            email = it.toString()
//
//                OnNavigate.LOGIN_TO_SHOE
//        }

//        dataStoreManager.user_pass.asLiveData().observe(requireActivity())
//        {
//            pass = it.toString()
//            ui.edPass.setText(pass)
//        }


    }
}