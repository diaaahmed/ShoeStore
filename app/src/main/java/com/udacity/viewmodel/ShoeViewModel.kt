package com.udacity.viewmodel

import android.util.Log
import androidx.appcompat.view.menu.ShowableListMenu
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.ShoeEvent
import com.udacity.model.Shoe

enum class Stata()
{
    SAVE,
    NO
}
class ShoeViewModel: ViewModel()
{
    private var _shoeList  = MutableLiveData<MutableList<Shoe>>()

    val shoeList: LiveData<MutableList<Shoe>> get() = _shoeList

    private var _state  = MutableLiveData<Stata>()

    val state: LiveData<Stata> get() = _state

    fun addShoe(shoe:Shoe)
    {
        _shoeList.value?.add(shoe)

    }

    init{
        Log.d("Shoe View Model", "Shoe View Model Created:")

        _shoeList.value = mutableListOf()

        _state.value = Stata.NO

    }

    fun onSave(shoe:Shoe)
    {
        addShoe(shoe)
        _state.value = Stata.SAVE
    }

    fun onSaveComplete()
    {
        _state.value = Stata.NO
    }

    override fun onCleared()
    {
        super.onCleared()
        Log.d("Shoe View Model", "Shoe View Model destroyed:")
    }
}