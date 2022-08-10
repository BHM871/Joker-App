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
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokeDayPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class JokeDayFragment : Fragment() {

  private lateinit var presenter: JokeDayPresenter

  private lateinit var progress: ProgressBar
  private lateinit var txtJoke: TextView
  private lateinit var icon: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = JokeDayPresenter(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_joke_day, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    progress = view.findViewById(R.id.progress_joke_day)
    txtJoke = view.findViewById(R.id.text_joke_day)
    icon= view.findViewById(R.id.icon_joke_day)

    presenter.findRandom()

    val button: FloatingActionButton = view.findViewById(R.id.fab_joke_day)
    button.setOnClickListener {
      presenter.findRandom()
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