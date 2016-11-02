package com.baculsoft.kotlin.android.views.activities

import android.R.id
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.views.fragments.ResultFragment
import kotlinx.android.synthetic.main.activity_result.*

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setToolbar()
        setFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar() {
        toolbar_result.navigationIcon = ContextCompat.getDrawable(this, R.mipmap.ic_arrow_back)
        setSupportActionBar(toolbar_result)
    }

    private fun setFragment() {
        val tag: String = ResultFragment::class.java.simpleName

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment: Fragment = ResultFragment::class.java.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fl_result, fragment, tag).commit()
        }
    }
}