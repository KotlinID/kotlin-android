package com.baculsoft.kotlin.android.views.adapters

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.pojo.TwitterSearchResult

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
class ResultAdapter(val results: List<TwitterSearchResult>) : RecyclerView.Adapter<ResultAdapter.ResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ResultHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_result, parent, false)
        return ResultHolder(view)
    }

    override fun onBindViewHolder(holder: ResultHolder?, position: Int) {
        val text: String = results[position].text
        holder?.tv_result?.text = convertToHtml(text)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    private fun convertToHtml(source: String): CharSequence? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            return Html.fromHtml(source)
        }
    }

    class ResultHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tv_result: TextView = itemView?.findViewById(R.id.tv_result) as TextView
    }
}