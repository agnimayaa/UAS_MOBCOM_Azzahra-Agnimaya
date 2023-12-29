package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Pembeli
import com.example.myapplication.databinding.ItemPembeliBinding

class PembeliAdapter : ListAdapter<Pembeli, PembeliAdapter.PembeliViewHolder>(PembeliDiffCallback()) {

    private var onItemClickListener: ((Pembeli) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pembeli) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PembeliViewHolder {
        val binding = ItemPembeliBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PembeliViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PembeliViewHolder, position: Int) {
        val pembeli = getItem(position)
        holder.bind(pembeli)
    }

    inner class PembeliViewHolder(private val binding: ItemPembeliBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(pembeli: Pembeli) {
            binding.pembeli = pembeli
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val pembeli = getItem(position)
                onItemClickListener?.invoke(pembeli)
            }
        }
    }
}

class PembeliDiffCallback : DiffUtil.ItemCallback<Pembeli>() {
    override fun areItemsTheSame(oldItem: Pembeli, newItem: Pembeli): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pembeli, newItem: Pembeli): Boolean {
        return oldItem == newItem
    }
}
