package com.cemaltuysuz.pagingexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cemaltuysuz.pagingexample.databinding.ActivityMainBinding
import com.cemaltuysuz.pagingexample.ui.fragments.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(binding.root)


    }
}