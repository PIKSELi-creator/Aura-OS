package com.auraos.system;// Замени на имя твоего пакета

import android.content.Context
import android.content.Intent

object AppManager {

    fun getInstalledApps(context: Context): List<AppInfo> {
        val packageManager = context.packageManager
        
        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfoList = packageManager.queryIntentActivities(mainIntent, 0)
        val appsList = mutableListOf<AppInfo>()

        for (resolveInfo in resolveInfoList) {
            val appLabel = resolveInfo.loadLabel(packageManager).toString()
            val packageName = resolveInfo.activityInfo.packageName
            val appIcon = resolveInfo.loadIcon(packageManager)

            appsList.add(AppInfo(appLabel, packageName, appIcon))
        }

        return appsList.sortedBy { it.label.lowercase() }
    }
}
