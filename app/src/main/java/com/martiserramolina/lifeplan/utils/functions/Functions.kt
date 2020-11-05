package com.martiserramolina.lifeplan.utils.functions

import android.app.Activity
import android.graphics.Typeface
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.martiserramolina.lifeplan.R
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.adapters.ItemAdapter
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.diffutils.ItemListDiffCallback
import com.martiserramolina.lifeplan.ui.adapters.recyclerview.viewholders.ItemViewHolder
import com.martiserramolina.lifeplan.viewmodels.interfaces.LoadListItemsViewModel
import java.text.SimpleDateFormat
import java.util.*

fun Activity.hideKeyboard() {
    ContextCompat.getSystemService(
        this, InputMethodManager::class.java
    )?.hideSoftInputFromWindow((currentFocus ?: View(this)).windowToken, 0)
}

fun Date.formatted(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)
}

fun String.formattedWithMaxLength(maxLength: Int): String {
    val positionLineBreak = indexOf("\n")
    return if (positionLineBreak != -1 && positionLineBreak <= maxLength)
        substring(0, positionLineBreak).trim() + "..."
    else if (length <= maxLength) this
    else substring(0, maxLength - 2).trim() + "..."
}

fun <T: ItemViewHolder<out ViewBinding, U>, S: ItemListDiffCallback<U>, U> RecyclerView
        .setupAutoLoadItemsFunctionality(
    lifecycleOwner: LifecycleOwner,
    itemAdapter: ItemAdapter<T, S, U>,
    loadListItemsViewModel: LoadListItemsViewModel<U>
) {
    val linearLayoutManager = LinearLayoutManager(context)
    layoutManager = linearLayoutManager
    adapter = itemAdapter
    setHasFixedSize(true)
    addItemDecoration(
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(context, R.drawable.div_rvi)!!)
        }
    )
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (linearLayoutManager.findLastVisibleItemPosition() == itemAdapter.itemCount)
                loadListItemsViewModel.fetchItemsIfNotFetched((itemAdapter.itemCount + 1).toLong())
        }
    })
    loadListItemsViewModel.itemsFetched.observe(lifecycleOwner) { itemAdapter.items = it }
}

fun showMessage(view: View, messageId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    (view.context as Activity).hideKeyboard()
    Snackbar.make(view, messageId, duration).apply {
        setBackgroundTint(ContextCompat.getColor(context, R.color.colorSnackBarBackground))
        setTextColor(ContextCompat.getColor(context, R.color.colorSnackBarText))
        setActionTextColor(ContextCompat.getColor(context, R.color.colorSnackBarActionText))
        setAction(R.string.close) { dismiss() }
    }.apply {
        getView().findViewById<TextView>(
            com.google.android.material.R.id.snackbar_text
        ).typeface = Typeface.createFromAsset(context.assets, "fonts/open_sans.ttf")
        getView().findViewById<TextView>(
            com.google.android.material.R.id.snackbar_action
        ).typeface = Typeface.createFromAsset(context.assets, "fonts/open_sans.ttf")
    }.show()
}