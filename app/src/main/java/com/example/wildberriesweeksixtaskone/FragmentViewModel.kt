package com.example.wildberriesweeksixtaskone

import android.content.ClipData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.random.Random
import android.content.ClipData.Item

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


class FragmentViewModel: ViewModel() {

    val randomValue = MutableLiveData<Int?>()
    val count = MutableLiveData<Int>()
    private var job: Job? = null
    var isCount = false
    var i = 0
    private var isPaused = false
    var randomNumber: Int? = null
    fun start() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {


                  while (isActive){
                      if (!isPaused){
                          randomNumber = addRandomNumber()
                          randomValue.postValue(randomNumber)
                      }
                  }

        }

        job = viewModelScope.launch(Dispatchers.IO) {
            while (isActive){

                if (!isPaused){
                    delay(1000)
                    i++
                    count.postValue(i)
                }
            }
        }



    }

    fun play() {
        isPaused = false
    }

    fun pause() {
        isPaused = true
    }

    fun reset() {
        pause()
        randomNumber = 10
        randomValue.postValue(randomNumber)
        i = 0
        count.postValue(i)
    }


    private fun addRandomNumber(): Int?{
        return Random.nextInt(1, 9)
    }

    fun getSelected(): LiveData<Int?>? {
        return randomValue
    }

}