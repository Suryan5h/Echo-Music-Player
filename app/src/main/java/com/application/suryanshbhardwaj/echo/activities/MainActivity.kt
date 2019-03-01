package com.application.suryanshbhardwaj.echo.activities

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.application.suryanshbhardwaj.echo.R
import com.application.suryanshbhardwaj.echo.adapters.navigationdrawer_adapter
import com.application.suryanshbhardwaj.echo.fragments.Mainscreen_fragment
import com.application.suryanshbhardwaj.echo.fragments.SongPlayingFragment

class MainActivity : AppCompatActivity() {

    var navigationDrawerIconsList: ArrayList<String> = arrayListOf()
    var imagesfornavdrawer = intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites,
            R.drawable.navigation_settings,R.drawable.navigation_aboutus)
    object Statified{
        var drawerLayout: DrawerLayout?=null
        var notificationManager: NotificationManager?=null

    }

    var trackNotificationBuilder: Notification?=null
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        MainActivity.Statified.drawerLayout = findViewById(R.id.drawer_layout)

        navigationDrawerIconsList.add("All Songs")
        navigationDrawerIconsList.add("Favourites")
        navigationDrawerIconsList.add("Settings")
        navigationDrawerIconsList.add("About Us")


        val toggle = ActionBarDrawerToggle(this@MainActivity,MainActivity.Statified.drawerLayout,toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        MainActivity.Statified.drawerLayout?.setDrawerListener(toggle)
        toggle.syncState()

        val mainscreenFragment = Mainscreen_fragment()
        this.supportFragmentManager
                .beginTransaction()
                .add(R.id.details_fragment, mainscreenFragment,"MainScreenFragment")
                .commit()

        var _navigationadapter = navigationdrawer_adapter(navigationDrawerIconsList,imagesfornavdrawer,this)
        _navigationadapter.notifyDataSetChanged()

        var navigationRecyclerview = findViewById<RecyclerView>(R.id.navigation_Recyclerview)
        navigationRecyclerview.layoutManager  = LinearLayoutManager(this)
        navigationRecyclerview.itemAnimator = DefaultItemAnimator()

        navigationRecyclerview.adapter = _navigationadapter
        navigationRecyclerview.setHasFixedSize(true)

        val intent= Intent(this@MainActivity, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this@MainActivity, System.currentTimeMillis().toInt(),intent,0)

        trackNotificationBuilder = Notification.Builder(this)
                .setContentTitle("A track is Playing at Background")
                .setSmallIcon(R.drawable.echo_icon)
                .setContentIntent(pIntent)
                .setOngoing(true)
                .setAutoCancel(true)
                .build()
        Statified.notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onStart() {
        super.onStart()
        try {
            Statified.notificationManager?.cancel(1978)

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        try {

            if(SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean){
                Statified.notificationManager?.notify(1978,trackNotificationBuilder)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            Statified.notificationManager?.cancel(1978)

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
