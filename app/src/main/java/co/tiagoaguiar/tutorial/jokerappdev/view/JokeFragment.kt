package co.tiagoaguiar.tutorial.jokerappdev.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokePresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class JokeFragment : Fragment() {

    private lateinit var presenter: JokePresenter

    private lateinit var categoryName: String

    private lateinit var progress: ProgressBar
    private lateinit var txtJoke: TextView
    private lateinit var icon: ImageView

    companion object {
        const val CATEGORY_KEY = "category"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = JokePresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_joke, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryName = arguments?.getString(CATEGORY_KEY).toString()
        activity?.findViewById<Toolbar>(R.id.toolbar)?.title = categoryName

        progress = view.findViewById(R.id.progress_joke)
        txtJoke = view.findViewById(R.id.text_joke)
        icon = view.findViewById(R.id.icon_joke)

        presenter.findBy(categoryName)

        val button: FloatingActionButton = view.findViewById(R.id.fab_joke)
        button.setOnClickListener {
            presenter.findBy(categoryName)
        }

    }

    fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSuccess(response: Joke) {
        txtJoke.text = response.text
        Picasso.get().load(response.iconUrl).into(icon)
    }

    fun onFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun hideProgress() {
        progress.visibility = View.GONE
    }
}