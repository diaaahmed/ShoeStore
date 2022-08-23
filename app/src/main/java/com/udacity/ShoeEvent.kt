package com.udacity

import com.udacity.model.Shoe

sealed class ShoeEvent
{
    data class AddShoes(val shoes:Shoe):ShoeEvent()

    object Loading:ShoeEvent()

    object InsertShoe:ShoeEvent()

    object ClearShoe:ShoeEvent()

}