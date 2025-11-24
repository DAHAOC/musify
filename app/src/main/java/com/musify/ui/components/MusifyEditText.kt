package com.musify.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.musify.R

class MusifyEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = android.R.attr.editTextStyle
) : TextInputEditText(context, attrs, defStyleAttr) {

    init {
        val textColor = ContextCompat.getColor(context, R.color.text)
        val hintColor = ContextCompat.getColor(context, R.color.hint)

        setTextColor(textColor)
        setHintTextColor(hintColor)

        background = AppCompatResources.getDrawable(context, R.drawable.edittext_border)
        setPadding(24, 24, 24, 24)
    }
}
