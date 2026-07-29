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
    private val appList: List<AppInfo>
) : BaseAdapter() {

    override fun getCount(): Int = appList.size

    override fun getItem(position: Int): Any = appList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_app, parent, false)
        
        val appInfo = appList[position]
        val iconView = view.findViewById<ImageView>(R.id.app_icon)
        val nameView = view.findViewById<TextView>(R.id.app_name)

        iconView.setImageDrawable(appInfo.icon)
        nameView.text = appInfo.label

        // Запуск приложения при нажатии на иконку
        view.setOnClickListener {
            val launchIntent = context.packageManager.getLaunchIntentForPackage(appInfo.packageName)
            if (launchIntent != null) {
                context.startActivity(launchIntent)
            }
        }

        return view
    }
}
