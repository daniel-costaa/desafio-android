package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.repository.PicPayRepository
import com.picpay.desafio.android.data.model.Resource
import com.picpay.desafio.android.data.model.UserDto
import kotlinx.coroutines.launch

class MainActivityViewModel(private val picPayRepository: PicPayRepository) : ViewModel() {
    private val _users = MutableLiveData<Resource<List<UserDto>>>()
    val users: LiveData<Resource<List<UserDto>>> = _users

    fun getUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            val resource = picPayRepository.getUsers()
            _users.postValue(resource)
        }
    }
}
