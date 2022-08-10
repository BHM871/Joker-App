package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.interfaces.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresenter(
    private val view: HomeFragment,
    private val data: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    fun findAllCategories(){
        view.showProgress()
        data.findAll(this)
    }

    @SuppressLint("Range")
    override fun onSuccess(response: List<String>){
        val start = 40
        val end = 190
        val interval = (end - start) / response.size

        val categories = response.mapIndexed {index, title ->
            val hsv = floatArrayOf(
                start + (interval * index).toFloat(),
                100.0f,
                100.0f
            )

            Category(title, Color.HSVToColor(hsv).toLong())
        }

        view.onSuccess(categories)
    }

    override fun onError(response: String){
        view.onFailure(response)
    }

    override fun onComplete(){
        view.hideProgress()
    }

}