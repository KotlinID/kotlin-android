package com.baculsoft.kotlin.android.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.baculsoft.kotlin.android.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Budi Oktaviyan Suryanto (budi@setipe.com)
 */
open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar_main.setTitle(R.string.app_name)
        toolbar_main.setSubtitle(R.string.app_desc)
        setSupportActionBar(toolbar_main)
    }
}