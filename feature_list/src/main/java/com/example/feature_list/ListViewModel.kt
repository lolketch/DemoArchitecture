package com.example.feature_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.MyCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(private val listRepository: ListRepositoryImpl) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _characters = MutableLiveData<List<MyCharacter>>()
    val characters: LiveData<List<MyCharacter>> = _characters

    fun fetchCharacters() {
        compositeDisposable.add(
            listRepository.fetchCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _characters.postValue(it.results)
                }, {
                    Log.e("ViewModelError", "$it")
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}