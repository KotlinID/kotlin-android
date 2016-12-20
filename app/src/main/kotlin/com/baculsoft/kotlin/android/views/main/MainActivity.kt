package com.baculsoft.kotlin.android.views.main

import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.baculsoft.kotlin.android.App
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearch
import com.baculsoft.kotlin.android.internal.injectors.component.ActivityComponent
import com.baculsoft.kotlin.android.internal.injectors.component.DaggerActivityComponent
import com.baculsoft.kotlin.android.internal.injectors.module.ActivityModule
import com.baculsoft.kotlin.android.utils.Commons
import com.baculsoft.kotlin.android.utils.Keyboards
import com.baculsoft.kotlin.android.utils.Navigators
import com.baculsoft.kotlin.android.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class MainActivity : BaseActivity(), MainView {

    companion object {
        lateinit var component: ActivityComponent
        lateinit var progressDialog: ProgressDialog
    }

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var commonUtils: Commons

    @Inject
    lateinit var keyboards: Keyboards

    @Inject
    lateinit var navigators: Navigators

    override fun getContentView(): Int = R.layout.activity_main

    override fun initComponents(savedInstanceState: Bundle?) {
        inject()
        onAttach()
        setToolbar()
        addEditTextListener()
        addButtonListener()
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

    override fun onShowProgressDialog() {
        progressDialog = commonUtils.getProgressDialog(this)
        if (!progressDialog.isShowing) {
            progressDialog.setMessage(resources.getString(R.string.message_search))
            progressDialog.show()
        }
    }

    override fun onDismissProgressDialog() {
        if (this.isFinishing) {
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (this.isDestroyed) {
                return
            }
        }

        if (progressDialog.isShowing) {
            progressDialog.dismiss()
            reset()
        }
    }

    override fun onShowError() {
        commonUtils.showGeneralError(cl_main, resources.getString(R.string.error_failed)).show()
    }

    override fun onConnectionError() {
        commonUtils.showGeneralError(cl_main, resources.getString(R.string.error_unknown)).show()
    }

    override fun onNavigateView(twitterSearch: TwitterSearch) {
        navigators.openResultActivity(this, twitterSearch)
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    private fun addEditTextListener() {
        tiet_main_query.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query: String = tiet_main_query.text.toString()
                val page: String = tiet_main_page.text.toString()

                presenter.validateSearch(query, page, btn_main)
            }
        })

        tiet_main_page.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query: String = tiet_main_query.text.toString()
                val page: String = tiet_main_page.text.toString()

                presenter.validateSearch(query, page, btn_main)
            }
        })
    }

    private fun addButtonListener() {
        btn_main.setOnClickListener { view ->
            val query: String = tiet_main_query.text.toString()
            val page: String = tiet_main_page.text.toString()
            val searchType: String = sp_main_type.selectedItem.toString().toLowerCase()
            val resultType: String = sp_main_result.selectedItem.toString().toLowerCase()

            presenter.getTwitterSearch(query, page, searchType, resultType)
        }
    }

    private fun setToolbar() {
        toolbar_main.setPadding(0, getStatusBarHeight() as Int, 0, 0)
        toolbar_main.setTitle(R.string.app_name)
        toolbar_main.setSubtitle(R.string.app_desc)
        setSupportActionBar(toolbar_main)
    }

    private fun reset() {
        tiet_main_query.text.clear()
        tiet_main_page.text.clear()
        sp_main_type.setSelection(0)
        sp_main_result.setSelection(0)
        btn_main.isEnabled = false
        keyboards.hide(cl_main, this)
        cl_main.requestFocus()
    }
}