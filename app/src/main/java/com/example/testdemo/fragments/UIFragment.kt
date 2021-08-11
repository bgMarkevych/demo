package com.example.testdemo.fragments

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.testdemo.R


class UIFragment : Fragment(R.layout.fragment_ui) {

    private lateinit var firstSection: ViewGroup
    private lateinit var secondSection: ViewGroup
    private lateinit var thirdSection: ViewGroup

    private val items = mutableListOf<CardView>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstSection = view.findViewById(R.id.first_column)
        secondSection = view.findViewById(R.id.second_column)
        thirdSection = view.findViewById(R.id.third_column)

        firstSection.setOnDragListener(DragListener())
        secondSection.setOnDragListener(DragListener())
        thirdSection.setOnDragListener(DragListener())

        items.addAll(
            getItemsFromParent(firstSection) +
                    getItemsFromParent(secondSection) +
                    getItemsFromParent(thirdSection)
        )
        items.forEach { card ->
            card.setOnClickListener {

                val color = when ((card.parent as View).id) {
                    R.id.first_column -> R.color.green_color
                    R.id.second_column -> R.color.orange_color
                    else -> R.color.red_color
                }

                com.example.testdemo.BottomSheetDialog.show(childFragmentManager, color)
            }
            card.setOnLongClickListener {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = DragShadowBuilder(it)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.startDragAndDrop(data, shadowBuilder, it, 0)
                } else {
                    it.startDrag(data, shadowBuilder, it, 0)
                }
                it.visibility = View.INVISIBLE

                return@setOnLongClickListener true
            }
        }
    }

    private fun getItemsFromParent(parent: ViewGroup): List<CardView> =
        parent.children.toList().filterIsInstance<CardView>()

    class DragListener : View.OnDragListener {

        override fun onDrag(p0: View?, p1: DragEvent?): Boolean {
            when (p1?.action) {
                DragEvent.ACTION_DROP -> {
                    val view = p1.localState as CardView
                    val owner = view.parent as ViewGroup
                    owner.removeView(view)
                    val container = p0 as ViewGroup
                    container.addView(view)
                    view.visibility = View.VISIBLE
                    val color = when (container.id) {
                        R.id.first_column -> R.color.green_color
                        R.id.second_column -> R.color.orange_color
                        else -> R.color.red_color
                    }
                    view.setCardBackgroundColor(ContextCompat.getColor(p0.context, color))
                }
                else -> {
                }
            }
            return true
        }
    }
}