package com.meli.notbored.util.view

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.appbar.MaterialToolbar
import com.meli.notbored.R

object UtilitiesView {
    fun showToolBar(toolbar: MaterialToolbar, baseContext: Context): MaterialToolbar{
        toolbar.navigationIcon = AppCompatResources.getDrawable(
                baseContext,
                R.drawable.ic_baseline_navigate_before_24
            )
        return toolbar
    }
}