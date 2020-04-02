package by.nrstudio.shambambukli.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.nrstudio.shambambukli.db.dao.CellDao

object Type {
    const val DEAD = 0
    const val LIVE = 1
    const val LIFE = 2
}

@Entity(tableName = CellDao.TABLE_NAME)
class Cell() : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
    var type: Int = (Type.DEAD..Type.LIVE).random()

    constructor(type: Int = Type.LIFE) : this() {
        this.type = type
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        type = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        @Suppress("unused")
        val CREATOR = createParcel { Cell(it) }
    }

}
