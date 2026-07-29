package com.auraos.system

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AppAdapter(
    private val context: Context,
    private val originalList: List<AppInfo>
) : BaseAdapter() {

    private var filteredList: List<AppInfo> = originalList

    override fun getCount(): Int = filteredList.size

    override fun getItem(position: Int): Any = filteredList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_app, parent, false)
        
        val appInfo = filteredList[position]
        val iconView = view.findViewById<ImageView>(R.id.app_icon)
        val nameView = view.findViewById<TextView>(R.id.app_name)

        iconView.setImageDrawable(appInfo.icon)
        nameView.text = appInfo.label

        view.setOnClickListener {
            val launchIntent = context.packageManager.getLaunchIntentForPackage(appInfo.packageName)
            if (launchIntent != null) {
                context.startActivity(launchIntent)
            }
        }

        return view
    }

    // Функция поиска по названию приложения
    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            originalList
        } else {
            originalList.filter { it.label.contains(query, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }
}
