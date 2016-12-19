package com.baculsoft.kotlin.android.views.result

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.baculsoft.kotlin.android.R
import com.baculsoft.kotlin.android.internal.data.local.TwitterSearchResult
import com.baculsoft.kotlin.android.utils.Dates
import java.util.*

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
class ResultAdapter constructor(
        val context: Context,
        val mDates: Dates,
        val mResults: List<TwitterSearchResult>
) : RecyclerView.Adapter<ResultAdapter.ResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ResultHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_result, parent, false)
        return ResultHolder(view)
    }

    override fun onBindViewHolder(holder: ResultHolder?, position: Int) {
        val text: String = mResults[position].text
        val date: Date = mDates.getDateTime(mResults[position].createdAt)
        val dateTime: String = mDates.getDateTime(date)

        holder?.resultText?.text = convertToHtml(text)
        holder?.resultDate?.text = String.format(context.resources.getString(R.string.text_posted), dateTime)
    }

    override fun getItemCount(): Int = mResults.size

    private fun convertToHtml(source: String): CharSequence {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            return Html.fromHtml(source)
        }
    }

    class ResultHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
        val resultText: TextView = itemView?.findViewById(R.id.tv_result_text) as TextView
        val resultDate: TextView = itemView?.findViewById(R.id.tv_result_date) as TextView
    }
}