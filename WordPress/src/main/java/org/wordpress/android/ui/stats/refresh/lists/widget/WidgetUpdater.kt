package org.wordpress.android.ui.stats.refresh.lists.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import javax.inject.Inject

interface WidgetUpdater {
    fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(context),
        appWidgetId: Int
    )

    fun updateAllWidgets(context: Context)
    fun delete(appWidgetId: Int)

    class StatsWidgetUpdaters
    @Inject constructor(viewsWidgetUpdater: ViewsWidgetUpdater, allTimeWidgetUpdater: AllTimeWidgetUpdater) {
        private val widgetUpdaters = listOf(viewsWidgetUpdater, allTimeWidgetUpdater)
        fun update(context: Context) {
            widgetUpdaters.forEach {
                it.updateAllWidgets(context)
            }
        }
    }
}
