package com.example.moviesubmissionandroidexp.movie.ui.customview

import android.content.Context
import android.content.res.Configuration
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.moviesubmissionandroidexp.movie.R

class ArcView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val path = Path()

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        // Draw background color
        canvas.drawColor(ContextCompat.getColor(context, R.color.teal_200))

        // Draw curve portion of background
        // Setup color
        paint.color = ContextCompat.getColor(context, R.color.white)

        val curveStartAndEndY = 0.6f * measuredHeight // <------ You can change this value based on your requirement
        // Set curve's control point's Y position (downwards bulge of curve) based on orientation
        var curveControlPointY = measuredHeight / 1.4f // <------ You can change this value based on your requirement
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Since in landscape mode, the curve will have greater arc length,
            // we need to give the curve more downwards bulge as compared to that in portrait mode.
            curveControlPointY = measuredHeight / 1.2f // <------ You can change this value based on your requirement
        }
        // Now we draw the entire path for curve
        path.moveTo(0f, curveStartAndEndY)
        path.quadTo(
            measuredWidth / 2f,
            curveControlPointY,
            measuredWidth.toFloat(),
            curveStartAndEndY
        )
        path.lineTo(measuredWidth.toFloat(), 0f)
        path.lineTo(0f, 0f)
        path.lineTo(0f, curveStartAndEndY)
        canvas.drawPath(path, paint)
    }
}