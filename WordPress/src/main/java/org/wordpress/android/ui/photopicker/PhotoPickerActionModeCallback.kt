package org.wordpress.android.ui.photopicker

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.appcompat.view.ActionMode.Callback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_START
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import org.wordpress.android.R
import org.wordpress.android.R.id
import org.wordpress.android.ui.utils.UiString.UiStringRes
import org.wordpress.android.ui.utils.UiString.UiStringText

class PhotoPickerActionModeCallback(private val viewModel: PhotoPickerViewModel) : Callback,
        LifecycleOwner {
    private lateinit var lifecycleRegistry: LifecycleRegistry
    override fun onCreateActionMode(
        actionMode: ActionMode,
        menu: Menu
    ): Boolean {
        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.handleLifecycleEvent(ON_START)
        viewModel.actionModeUiModel.observe(this, Observer { uiModel ->
            if (uiModel.showConfirmAction && menu.size() == 0) {
                val inflater = actionMode.menuInflater
                inflater.inflate(R.menu.photo_picker_action_mode, menu)
            }

            if (uiModel.actionModeTitle is UiStringText) {
                actionMode.title = uiModel.actionModeTitle.text
            } else if (uiModel.actionModeTitle is UiStringRes) {
                actionMode.setTitle(uiModel.actionModeTitle.stringRes)
            }
        })
        viewModel.onShowActionMode.observe(this, Observer {
            if (it?.peekContent() == false && !it.hasBeenHandled) {
                it.getContentIfNotHandled()
                actionMode.finish()
            }
        })
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onActionItemClicked(
        mode: ActionMode,
        item: MenuItem
    ): Boolean {
        if (item.itemId == id.mnu_confirm_selection) {
            viewModel.performInsertAction()
            return true
        }
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode) {
        viewModel.clearSelection()

        lifecycleRegistry.handleLifecycleEvent(ON_STOP)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}