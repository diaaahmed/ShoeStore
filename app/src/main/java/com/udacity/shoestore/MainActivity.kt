package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udacity.datastore.DataStoreManager
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{

    private val ui by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var dataStoreManager: DataStoreManager
    var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ui.root)

       // dataStoreManager = DataStoreManager(this)

//        dataStoreManager.user_email.asLiveData().observe(this){mail->
//
//            val graph = this.findNavController(R.id.nav_host).graph
//
//            if(mail.toString() =="")
//            {
//                Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show()
//
////                graph.setStartDestination(R.id.loginFragment)
////                findNavController(R.id.nav_host).graph = graph
////                findNavController(R.id.nav_host)
////                    .graph.setStartDestination(R.id.loginFragment)
//
////                nav_host.graph.id = R.id.loginFragment
////                findNavController(R.id.nav_host).graph = nav_host.graph
//
//            }
//            else
//            {
////                findNavController(R.id.nav_host)
////                    .graph.setStartDestination(R.id.shoeList)
//
////                graph.setStartDestination(R.id.shoeList)
////                findNavController(R.id.nav_host).graph = graph
//                findNavController(R.id.nav_host)
//                    .navigate(R.id.action_loginFragment_to_shoeList)
//                Toast.makeText(this, "email is ${mail.toString()}", Toast.LENGTH_SHORT).show()
//
//            }
//        }

    }
}