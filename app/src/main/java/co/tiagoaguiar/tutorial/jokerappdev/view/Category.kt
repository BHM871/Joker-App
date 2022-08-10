package co.tiagoaguiar.tutorial.jokerappdev.view

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class CategoryItem(val category: Category) : Item<CategoryItem.CategoryItemHolder>() {

    class CategoryItemHolder(view: View) : GroupieViewHolder(view){

    }

    override fun createViewHolder(itemView: View): CategoryItemHolder = CategoryItemHolder(itemView)

    override fun bind(viewHolder: CategoryItemHolder, position: Int) {
        viewHolder.itemView.findViewById<TextView>(R.id.text_category).text = category.name
        viewHolder.itemView.findViewById<LinearLayout>(R.id.container_category).setBackgroundColor(category.color.toInt())

    }

    override fun getLayout(): Int = R.layout.item_actegory

}