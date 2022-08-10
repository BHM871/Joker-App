package co.tiagoaguiar.tutorial.jokerappdev.interfaces

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke

interface JokeCallback {

    fun onSuccess(response: Joke)
    fun onError(response: String)
    fun onComplete()

}