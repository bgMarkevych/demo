package com.example.testdemo

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class MainViewModelFactory(
    private val model: MainModel,
    owner: SavedStateRegistryOwner,
    savedState: Bundle?
) :
    AbstractSavedStateViewModelFactory(owner, savedState) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = modelClass.getConstructor(MainModel::class.java, SavedStateHandle::class.java)
        .newInstance(model, handle)
}