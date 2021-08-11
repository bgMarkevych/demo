package com.example.testdemo

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.testdemo.model.LocationResponse
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(
    private val model: MainModel,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val APP_STATE_KEY = "APP_STATE_KEY"

        val forbiddenCountries = listOf("UA", "FR", "IT")
    }

    private val disposables = CompositeDisposable()
    private var appStateInternal: AppState = AppState.Splash
        get() = savedStateHandle.get<AppState>(APP_STATE_KEY) ?: AppState.Splash
        set(value) {
            field = value
            appState.postValue(value)
            savedStateHandle.set(APP_STATE_KEY, value)
        }
    var appState = savedStateHandle.getLiveData(APP_STATE_KEY, appStateInternal)

    fun onCreate(context: Context) {
        if (appState.value != AppState.Splash) return

        val d = model.checkLocation()
            .map(this::checkLocation)
            .subscribe({ appStateInternal = it }) { t ->
                Toast.makeText(
                    context,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()

                t.printStackTrace()
            }
        disposables.add(d)
    }

    private fun checkLocation(response: LocationResponse): AppState =
        if (forbiddenCountries.contains(response.countryCode)) AppState.Web else AppState.Native

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }


}