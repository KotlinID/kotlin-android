package com.baculsoft.kotlin.android.views.result

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.baculsoft.kotlin.android.App
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearchResult
import com.baculsoft.kotlin.android.internal.injectors.component.ActivityComponent
import com.baculsoft.kotlin.android.internal.injectors.component.DaggerActivityComponent
import com.baculsoft.kotlin.android.internal.injectors.module.ActivityModule
import com.baculsoft.kotlin.android.utils.Dates
import com.baculsoft.kotlin.android.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_result.*
import javax.inject.Inject

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class ResultActivity : BaseActivity(), ResultView {

    companion object {
        lateinit var component: ActivityComponent
    }

    @Inject
    lateinit var presenter: ResultPresenter

    @Inject
    lateinit var dates: Dates

    override fun getContentView(): Int = R.layout.activity_result

    override fun initComponents(savedInstanceState: Bundle?) {
        inject()
        onAttach()
        setToolbar()
    }

    override fun inject() {
        component = DaggerActivityComponent.builder().applicationComponent(App.component)
                                                     .activityModule(ActivityModule(this))
                                                     .build()
        component.inject(this)
    }

    override fun onAttach() {
        presenter.onAttach(this)
    }

    override fun onDetach() {
        presenter.onDetach()
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar() {
        toolbar_result.setPadding(0, getStatusBarHeight() as Int, 0, 0)
        toolbar_result.navigationIcon = ContextCompat.getDrawable(this, R.mipmap.ic_arrow_back)
        toolbar_result.title = resources.getString(R.string.text_search_result)
        toolbar_result.subtitle = String.format(resources.getString(R.string.text_search_count), presenter.getCount(this))
        setSupportActionBar(toolbar_result)
        setAdapter(presenter.getResults(this))
    }

    private fun setAdapter(results: List<TwitterSearchResult>) {
        val adapter: ResultAdapter = ResultAdapter(this, dates, results)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

        rv_result.layoutManager = linearLayoutManager
        rv_result.smoothScrollToPosition(rv_result.bottom)
        rv_result.adapter = adapter
    }
}