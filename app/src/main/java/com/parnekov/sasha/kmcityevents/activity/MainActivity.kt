package com.parnekov.sasha.kmcityevents.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.ShareCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.adapter.KMViewPagerAdapter
import com.parnekov.sasha.kmcityevents.firebase.ALL
import com.parnekov.sasha.kmcityevents.firebase.TODAY
import com.parnekov.sasha.kmcityevents.firebase.TOMORROW
import com.parnekov.sasha.kmcityevents.fragments.AllEventsFragment
import com.parnekov.sasha.kmcityevents.fragments.TodayEventsFragment
import com.parnekov.sasha.kmcityevents.fragments.TomorrowEventsFragment



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val titles: MutableList<String> = mutableListOf(ALL, TODAY, TOMORROW)

    private var fragments: MutableList<Fragment> =
        mutableListOf(AllEventsFragment(), TodayEventsFragment(), TomorrowEventsFragment())

    private var kmViewPagerAdapter: KMViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_layout)

        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        kmViewPagerAdapter = KMViewPagerAdapter(supportFragmentManager)
        kmViewPagerAdapter?.addFragments(fragments, titles)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = kmViewPagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.nav_add_event -> onNext()
            R.id.nav_item_share -> shareIntent()
            R.id.nav_item_feedback -> feedbackIntent()
            R.id.nav_item_about -> aboutDialog()
            R.id.nav_item_logout -> logOut()
            else -> Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun shareIntent() {
        ShareCompat.IntentBuilder
            .from(this)
            .setType("text/plain")
            .setChooserTitle(getString(R.string.app_name))
            .setText(getString(/*R.string.dialog_app_url*/R.string.text_nav_share)) // must be url app
            .startChooser()
    }

    private fun feedbackIntent() {
        val url = getString(R.string.url_app_)
        val feedbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(feedbackIntent)
    }

    private fun aboutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("About")
        builder.setPositiveButton(getString(R.string.text_dialog_Ok)) { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
    private fun onNext(){
        val intent = Intent(this, AddEventActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun logOut(){
        FirebaseAuth.getInstance().signOut()
        finish()
        startActivity(Intent(this@MainActivity, SplashActivity::class.java))
    }
}
// TODO CALL INTENT (FAB)
fun callIntent(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
    context.startActivity(intent)
}
