package com.example.moviedb.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import com.example.moviedb.R
import kotlin.math.max

class AutoGridLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): GridLayout(context, attrs, defStyleAttr) {

    private var defaultColumnCount = 0
    private var columnWidth = 0

    init {
        var a = getContext().obtainStyledAttributes(attrs, R.styleable.AutoGridLayout, 0, defStyleAttr)
        try {
            columnWidth = a.getDimensionPixelSize(R.styleable.AutoGridLayout_columnWidth, 0)
            val set = intArrayOf(android.R.attr.columnCount)
            a = getContext().obtainStyledAttributes(attrs, set, 0, defStyleAttr)
            defaultColumnCount = a.getInt(0, 10)
        } finally {
            a.recycle()
        }
        columnCount = 1
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        val width = MeasureSpec.getSize(widthSpec)
        if (columnWidth > 0 && width > 0) {
            val totalSpace = width - paddingRight - paddingLeft
            val columnCount = max(1, totalSpace / columnWidth)
            setColumnCount(columnCount)
        } else {
            columnCount = defaultColumnCount
        }
    }
}