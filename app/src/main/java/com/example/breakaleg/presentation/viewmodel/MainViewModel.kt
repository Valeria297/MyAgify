package com.example.breakaleg.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResultData
import com.example.domain.entities.NameEntity
import com.example.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAgeByNameUseCase: GetAgeByNameUseCase,
    private val addFavoriteNameUseCase: AddFavoriteNameUseCase,
    private val getFavoriteNamesUseCase: GetFavoriteNamesUseCase,
    private val getFavoriteNameByIdUseCase: GetFavoriteNameByIdUseCase,
    private val removeFavoriteNameUseCase: RemoveFavoriteNameUseCase
): ViewModel() {

    private var _age = MutableLiveData<ResultData<Int>>()
    val age: LiveData<ResultData<Int>> get() = _age

    private var _names = MutableLiveData<ResultData<List<NameEntity>>>()
    val names: LiveData<ResultData<List<NameEntity>>> get() = _names

    private var _name = MutableLiveData<ResultData<NameEntity>>()
    val name: LiveData<ResultData<NameEntity>> get() = _name

    fun getAgeByName(name: String) {
        viewModelScope.launch {
            val result = getAgeByNameUseCase(name = name)
            _age.value = result
        }
    }

    fun getFavoriteNames() {
        viewModelScope.launch {
            val result = getFavoriteNamesUseCase()
            _names.value = result
        }
    }

    fun getFavoriteNameById(id: Int) {
        viewModelScope.launch {
            val result = getFavoriteNameByIdUseCase(id = id)
            _name.value = result
        }
    }

    fun addFavoriteName(entity: NameEntity) {
        viewModelScope.launch {
            addFavoriteNameUseCase(entity = entity)
        }
    }

    fun removeFavoriteName(entity: NameEntity) {
        viewModelScope.launch {
            removeFavoriteNameUseCase(entity = entity)
        }
    }
}