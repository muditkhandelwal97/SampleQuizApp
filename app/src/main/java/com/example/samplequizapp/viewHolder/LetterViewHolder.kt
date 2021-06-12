package com.example.samplequizapp.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplequizapp.R

class LetterViewHolder internal constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    var textView: TextView = itemView.findViewById(R.id.tvMove)

}