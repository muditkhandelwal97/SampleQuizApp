package com.example.samplequizapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.samplequizapp.R
import com.example.samplequizapp.`interface`.LetterSelectedCallback
import com.example.samplequizapp.viewHolder.LetterViewHolder


class JumbledWordsAdapter internal constructor(var c: Context, var items: CharArray, private val letterSelectedCallback: LetterSelectedCallback):
    RecyclerView.Adapter<LetterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view: View =
            LayoutInflater.from(c).inflate(R.layout.item_letter_view_holder, parent, false)
        return LetterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.textView.text = items[position].toString()
        holder.textView.setOnClickListener {
            letterSelectedCallback.onLetterSelected(holder.textView.text[0])
        }
    }

    fun setLettersList(items: CharArray) {
        addRandomLettersAndShuffle(items)
        this.items = items
        notifyDataSetChanged()
    }

    fun addRandomLettersAndShuffle(items: CharArray): CharArray {
        //Todo implementation
        return items
    }

}