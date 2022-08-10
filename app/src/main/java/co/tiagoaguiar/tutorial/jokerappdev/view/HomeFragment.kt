package co.tiagoaguiar.tutorial.jokerappdev.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.presentation.HomePresenter
import com.xwray.groupie.GroupieAdapter

class HomeFragment : Fragment() {

    private lateinit var presenter: HomePresenter

    private lateinit var progress: ProgressBar

    private lateinit var adapter: GroupieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = GroupieAdapter()
        progress = view.findViewById(R.id.progress_main)

        if(adapter.itemCount == 0){
            presenter.findAllCategories()
        }

        val rvMain: RecyclerView = view.findViewById(R.id.recycler_fragment_home)
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        rvMain.adapter = adapter

        adapter.setOnItemClickListener { item, _ ->
            val bundle = Bundle()
            val categoryName = (item as CategoryItem).category.name
            bundle.putString(JokeFragment.CATEGORY_KEY, categoryName)
            findNavController().navigate(R.id.action_nav_home_to_nav_joke, bundle)
        }

    }

    fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSuccess(response: List<Category>) {
        val categories = response.map {
            CategoryItem(it)
        }

        adapter.clear()
        adapter.addAll(categories)
        adapter.notifyDataSetChanged()
    }

    fun onFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun hideProgress() {
        progress.visibility = View.GONE
    }
}