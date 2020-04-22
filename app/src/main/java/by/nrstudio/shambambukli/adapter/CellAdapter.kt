package by.nrstudio.shambambukli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.nrstudio.shambambukli.R
import by.nrstudio.shambambukli.adapter.base.BaseSupportAdapter
import by.nrstudio.shambambukli.adapter.base.BaseViewHolder
import by.nrstudio.shambambukli.model.Cell
import by.nrstudio.shambambukli.model.Type
import by.nrstudio.shambambukli.ui.model.cells.CellsViewModel

class CellAdapter(private val viewModel: CellsViewModel) : BaseSupportAdapter<Cell>() {

    init {
        list = viewModel.getState().data
    }

    fun getItem(position: Int) = list[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CellViewHolder(inflater.inflate(R.layout.item_cell, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CellViewHolder).bind(getItem(position))
    }
}

class CellViewHolder(
    view: View
) : BaseViewHolder<Cell>(view) {

    private var txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
    private var txtSubTitle: TextView = itemView.findViewById(R.id.txtSubTitle)
    private var imgMain: ImageView = itemView.findViewById(R.id.imgMain)

    override fun bind(model: Cell) {
        txtTitle.text = when (model.type) {
            Type.LIFE -> txtTitle.context?.getString(R.string.cells_title_life)
            Type.LIVE -> txtTitle.context?.getString(R.string.cells_title_live)
            else -> txtTitle.context?.getString(R.string.cells_title_dead)
        }
        txtSubTitle.text = when (model.type) {
            Type.LIFE -> txtTitle.context?.getString(R.string.cells_subtitle_life)
            Type.LIVE -> txtTitle.context?.getString(R.string.cells_subtitle_live)
            else -> txtTitle.context?.getString(R.string.cells_subtitle_dead)
        }

        imgMain.setImageResource(
            when (model.type) {
                Type.LIFE -> R.drawable.ic_life
                Type.LIVE -> R.drawable.ic_live
                else -> R.drawable.ic_dead
            }
        )

        imgMain.setBackgroundResource(
            when (model.type) {
                Type.LIFE -> R.drawable.bg_life
                Type.LIVE -> R.drawable.bg_live
                else -> R.drawable.bg_dead
            }
        )
    }
}
