package co.tiagoaguiar.tutorial.jokerappdev.data

import android.os.Handler
import android.os.Looper

class CategoryRemoteDataSource {

    fun findAll(callback: ListCategoryCallback){
        Handler(Looper.getMainLooper()).postDelayed({
            val categories = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )
            callback.onSuccess(categories)
            //callback.onError("ERROR")

            callback.onComplete()
        },2000)
    }

}