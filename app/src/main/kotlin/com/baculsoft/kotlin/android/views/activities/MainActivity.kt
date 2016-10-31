package com.baculsoft.kotlin.android.views.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.views.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        setFragment()
    }

    private fun setFragment() {
        val tag: String = MainFragment::class.java.simpleName

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment: Fragment = MainFragment::class.java.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fl_main, fragment, tag).commit()
        }
    }
}