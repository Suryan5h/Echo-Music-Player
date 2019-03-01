package com.application.suryanshbhardwaj.echo

import android.os.Parcel
import android.os.Parcelable

class Songs(var songId: Long, var songTitle: String, var artist: String, var songData: String, var dateAdded: Long) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong()) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0!!.writeLong(songId)
        p0.writeString(songTitle)
        p0.writeString(artist)
        p0.writeString(songData)
        p0.writeLong(dateAdded)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Songs> {
        override fun createFromParcel(parcel: Parcel): Songs {
            return Songs(parcel)
        }

        override fun newArray(size: Int): Array<Songs?> {
            return arrayOfNulls(size)
        }
    }

    object Statified{
        var nameComparator: Comparator<Songs> = Comparator<Songs>{song1, song2 ->
            val songone = song1.songTitle.toUpperCase()
            val songtwo = song2.songTitle.toUpperCase()
            songone.compareTo(songtwo)
        }

        var dateComparator: Comparator<Songs> = Comparator<Songs>{song1,song2->
            val songOne = song1.dateAdded.toDouble()
            val songTwo = song2.dateAdded.toDouble()
            songTwo.compareTo(songOne)
        }
    }

}