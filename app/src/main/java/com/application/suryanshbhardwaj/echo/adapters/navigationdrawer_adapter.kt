package com.application.suryanshbhardwaj.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.application.suryanshbhardwaj.echo.R
import com.application.suryanshbhardwaj.echo.activities.MainActivity
import com.application.suryanshbhardwaj.echo.fragments.AboutUsFragment
import com.application.suryanshbhardwaj.echo.fragments.FavoriteFragment
import com.application.suryanshbhardwaj.echo.fragments.Mainscreen_fragment
import com.application.suryanshbhardwaj.echo.fragments.SeetingsFragment

class navigationdrawer_adapter(_contentList: ArrayList<String>, _getImages: IntArray, _context: Context)
    : RecyclerView.Adapter<navigationdrawer_adapter.NavViewHolder>() {

    var content_list: ArrayList<String>? = null
    var getImages: IntArray? = null
    var mContext: Context? = null

    init {
        this.content_list = _contentList
        this.getImages = _getImages
        this.mContext = _context
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NavViewHolder {

        var itemview = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_navigationdrawer, parent, false)
        val returnThis = NavViewHolder(itemview)
        return returnThis
    }

    override fun getItemCount(): Int {

        return (content_list as ArrayList).size
    }

    override fun onBindViewHolder(holder: NavViewHolder?, position: Int) {

        holder?.icon_get?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_get?.setText(content_list?.get(position))
        holder?.content_holder?.setOnClickListener({
            if (position == 0) {
                val mainScreenFragment = Mainscreen_fragment()
                (mContext as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.details_fragment, mainScreenFragment)
                        .commit()
            } else if (position == 1) {
                val favFragment = FavoriteFragment()
                (mContext as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.details_fragment, favFragment)
                        .commit()
            } else if (position == 2) {
                val settingsFragment = SeetingsFragment()
                (mContext as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.details_fragment, settingsFragment)
                        .commit()
            } else {
                val aboutusFragment = AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.details_fragment, aboutusFragment)
                        .commit()
            }
            MainActivity.Statified.drawerLayout?.closeDrawers()
        })
    }

    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var icon_get: ImageView? = null
        var text_get: TextView? = null
        var content_holder: RelativeLayout? = null

        init {
            icon_get = itemView?.findViewById(R.id.icon_navdrawer)
            text_get = itemView?.findViewById(R.id.text_navdrawer)
            content_holder = itemView?.findViewById(R.id.navdrawer_item_content_holder)

        }
    }


}