package com.example.testdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var container: ViewGroup

    @Inject
    lateinit var model: MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(model, this, savedInstanceState))
                .get(MainViewModel::class.java)
        viewModel.appState.observe(this) {
            if (isFragmentAttached(it.clazz.name)) return@observe

            showFragment(it.clazz.newInstance() as Fragment)
        }

        viewModel.onCreate(this)

    }

    private fun isFragmentAttached(tag: String): Boolean =
        supportFragmentManager.findFragmentByTag(tag) != null

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.name)
            .commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.appState.removeObservers(this)
    }
}