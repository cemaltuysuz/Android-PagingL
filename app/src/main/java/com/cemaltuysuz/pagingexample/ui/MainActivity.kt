package com.cemaltuysuz.pagingexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.databinding.ActivityMainBinding
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import com.cemaltuysuz.pagingexample.ui.fragments.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
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