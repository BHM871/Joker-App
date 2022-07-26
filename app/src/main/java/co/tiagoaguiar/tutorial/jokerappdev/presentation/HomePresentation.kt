package co.tiagoaguiar.tutorial.jokerappdev.presentation

import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeFragment

class HomePresentation(
    private val view: HomeFragment,
    private val data: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback{

    fun findAllCategories(){
        view.showProgress()
        data.findAll(this)
    }

    override fun onSuccess(response: List<String>){
//        val categories = mutableListOf<CategoryItem>()
//
//        for(category in response){
//            categories.add(CategoryItem(category))
//        }

        view.onSuccess(response)
    }

    override fun onError(response: String){
        /*
        çdkna~sidh
        dlajdoiqp]
        lqwkfwp
        qwlbfoq
        qwbdquiwodpmqw
         */
        view.onFailure(response)
    }

    override fun onComplete(){
        /*
        bd.disconnected()
        stream.close()
        buffer.close()
         */
        view.hideProgress()
    }

}