package com.cemaltuysuz.pagingexample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.databinding.ActivityMainBinding
import com.cemaltuysuz.pagingexample.model.User
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/*
* Api Test
*
* */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var request: Api
    val disposable = CompositeDisposable()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inpBtn.setOnClickListener{
            requestApi(binding.inp.text.toString())
        }

    }

    fun requestApi(username:String){
        disposable.add(
            request.findUser(username).
            subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserItem>(){
                    override fun onSuccess(t: UserItem?) {
                        t?.let {
                            print("Response ->>> $t")
                            Toast.makeText(applicationContext,t.avatar_url,Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(e: Throwable?) {
                        Toast.makeText(applicationContext,"err : $e?.message",Toast.LENGTH_LONG).show()
                    }
                })
        )
    }
}