package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.interfaces.ListCategoryCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource {

    fun findAll(callback: ListCategoryCallback) {
        HTTPClient.retrofit()
            .create(RetrofitModel::class.java)
            .findAllCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                        callback.onComplete()
                    } else{
                        val error = response.errorBody()?.string()
                        callback.onError(error ?: "Erro desconhecido")
                        callback.onComplete()
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message ?: "Erro do servidor")
                    callback.onComplete()
                }

            })
    }

}