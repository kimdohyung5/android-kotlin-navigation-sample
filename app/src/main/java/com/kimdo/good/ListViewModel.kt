package com.kimdo.good

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kimdo.good.models.Animal
import com.kimdo.good.models.AnimalResponse
import com.kimdo.good.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel(application:Application): AndroidViewModel(application) {

    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>()}
    val loading by lazy { MutableLiveData<Boolean>()}

    fun refresh() {
        getAnimals()
    }

    private fun getAnimals() {
//        val a1 = Animal("alligator")
//        val a2 = Animal("bee")
//        val a3 = Animal("cat")
//        val a4 = Animal("dog")
//        val a5 = Animal("elephant")
//        val a6 = Animal("flamingo")
//        val a7 = Animal("dog")
//        val a8 = Animal("elephant")
//        val a9 = Animal("flamingo")
        
        
        RetrofitBuilder.api.getKey().enqueue( object: Callback<ApiKey> {
            override fun onResponse(call: Call<ApiKey>, response: Response<ApiKey>) {
                if(response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                    val key = response.body()!!

                    RetrofitBuilder.api.getAnimals(key.key!!).enqueue( object: Callback<AnimalResponse> {
                        override fun onResponse(
                            call: Call<AnimalResponse>,
                            response: Response<AnimalResponse>
                        ) {
                            if(response.isSuccessful) {
                                Log.d(TAG, "onResponse: ${response.body()}")
                                val animalResponse = response.body()

                                animals.value = animalResponse?.toList()!!
                                loadError.value = false
                                loading.value = false

                            }
                        }

                        override fun onFailure(call: Call<AnimalResponse>, t: Throwable) {
                            Log.d(TAG, "onFailure: ${t.localizedMessage}")
                        }

                    })
                }
            }

            override fun onFailure(call: Call<ApiKey>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.localizedMessage}")
            }

        })

//        val animalList = arrayListOf( a1, a2, a3, a4, a5, a6, a7, a8, a9)

    }
    
    companion object {
        val TAG = "ListViewModel"
    }
}