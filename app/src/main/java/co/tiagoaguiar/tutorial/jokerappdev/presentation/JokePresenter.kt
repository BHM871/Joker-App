package co.tiagoaguiar.tutorial.jokerappdev.presentation

import co.tiagoaguiar.tutorial.jokerappdev.data.JokeRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.interfaces.JokeCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.view.JokeFragment

class JokePresenter(private val view: JokeFragment,
                    private val data: JokeRemoteDataSource = JokeRemoteDataSource()
) : JokeCallback {

    fun findBy(categoryName: String){
        view.showProgress()
        data.findBy(categoryName, this)
    }

    override fun onSuccess(response: Joke) {
        view.onSuccess(response)
    }

    override fun onError(response: String) {
        view.onFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }
}