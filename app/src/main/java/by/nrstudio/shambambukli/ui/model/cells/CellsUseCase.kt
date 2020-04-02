package by.nrstudio.shambambukli.ui.model.cells

import android.util.Log
import by.nrstudio.shambambukli.model.Cell
import by.nrstudio.shambambukli.model.Type
import by.nrstudio.shambambukli.ui.model.base.Action
import by.nrstudio.shambambukli.ui.model.base.BaseDbUseCase
import kotlinx.coroutines.channels.ReceiveChannel

class CellsUseCase : BaseDbUseCase() {

    fun addItem(state: CellsViewState): ReceiveChannel<Action<CellsViewState>> = produceActions {
        send { copy(loading = true, error = null) }
        try {
            val item = Cell()
            val items = state.data
            db.cellDao().insert(item)
            items.add(item)

            if (items.size > 2) {
                var isLife = 0

                items.forEach {

                    when (it.type) {
                        Type.LIVE -> {
                            if (isLife < 0) {
                                isLife = 0
                            }
                            isLife++
                            if (isLife == 3 && items.last() == it) {
                                isLife = 0
                                val new = Cell(type = Type.LIFE)
                                items.add(new)
                                db.cellDao().insert(new)
                            }
                        }
                        Type.DEAD -> {
                            if (isLife > 0) {
                                isLife = 0
                            }
                            isLife--
                            if (isLife == -3 && items.last() == it) {
                                isLife = 0
                                val position = items.indexOf(it)
                                val previous = items.getOrNull(position - 3)
                                if (previous?.type == Type.LIFE) {
                                    items.remove(previous)
                                    db.cellDao().delete(previous)
                                }
                            }
                        }
                        //  it.type == Type.LIFE  -> isLife = 0
                        else -> {
                            isLife = 0
                        }
                    }
                }
            }

            send { copy(data = items, loading = false) }
        } catch (e: Exception) {
            send { copy(error = e, loading = false) }
            Log.e("CellsUseCase", e.message, e)
        }
    }

    fun loadData(): ReceiveChannel<Action<CellsViewState>> = produceActions {
        send { copy(data = mutableListOf(), loading = true, error = null) }
        try {
            val items = db.cellDao().getItems()
            send { copy(data = items, loading = false) }
        } catch (e: Exception) {
            send { copy(error = e, loading = false) }
            Log.e("CellsUseCase", e.message, e)
        }
    }

    fun clearData(): ReceiveChannel<Action<CellsViewState>> = produceActions {
        send { copy(loading = true, error = null) }
        try {
            db.cellDao().deleteAll()
            send { copy(data = mutableListOf(), loading = false) }
        } catch (e: Exception) {
            send { copy(error = e, loading = false) }
            Log.e("CellsUseCase", e.message, e)
        }
    }
}