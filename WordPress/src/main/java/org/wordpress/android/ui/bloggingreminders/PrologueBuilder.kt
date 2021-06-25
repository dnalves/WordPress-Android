package org.wordpress.android.ui.bloggingreminders

import org.wordpress.android.R
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.HighEmphasisText
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Illustration
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersItem.Title
import org.wordpress.android.ui.bloggingreminders.BloggingRemindersViewModel.UiState.PrimaryButton
import org.wordpress.android.ui.utils.ListItemInteraction
import org.wordpress.android.ui.utils.UiString.UiStringRes
import javax.inject.Inject

class PrologueBuilder
@Inject constructor() {
    fun buildUiItems(): List<BloggingRemindersItem> {
        return listOf(Illustration(R.drawable.img_illustration_celebration_150dp),
                Title(UiStringRes(R.string.set_your_blogging_reminders_title)),
                HighEmphasisText(UiStringRes(R.string.post_publishing_set_up_blogging_reminders_message))
        )
    }

    fun buildUiItemsForSettings(): List<BloggingRemindersItem> {
        return listOf(
                Illustration(R.drawable.img_illustration_celebration_150dp),
                Title(UiStringRes(R.string.set_your_blogging_reminders_title)),
                HighEmphasisText(UiStringRes(R.string.set_up_blogging_reminders_message))
        )
    }

    fun buildPrimaryButton(
        isFirstTimeFlow: Boolean,
        onContinue: (Boolean) -> Unit
    ): PrimaryButton {
        return PrimaryButton(
                UiStringRes(R.string.set_your_blogging_reminders_button),
                enabled = true,
                ListItemInteraction.create(isFirstTimeFlow, onContinue)
        )
    }
}
