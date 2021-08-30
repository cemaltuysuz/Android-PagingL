package com.cemaltuysuz.pagingexample.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cemaltuysuz.pagingexample.model.User
import com.cemaltuysuz.pagingexample.model.UserItem
import com.cemaltuysuz.pagingexample.service.retrofit.Api
import com.cemaltuysuz.pagingexample.utils.Resource
import com.cemaltuysuz.pagingexample.utils.Status
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UserRepo @Inject constructor(api:Api) {

    private val request = api
    private val disposable = CompositeDisposable()

    // User
    private val response  = MutableLiveData<Resource<UserItem>>()
    val getSearchedUser :LiveData<Resource<UserItem>>
    get() = response

    // User's followers
    private val userFollowers = MutableLiveData<User>()
    val getUserFollowers : LiveData<User>
    get() = userFollowers


    fun searchUser(username:String){
        disposable.add(
            request.findUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserItem>(){
                    override fun onSuccess(t: UserItem?) {
                        t?.let {
                            response.value = Resource(Status.SUCCESS,it,null)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        response.value = Resource(Status.ERROR,null,e?.message)
                    }

                })
        )
    }

}