package com.example.the_cat_api.ui.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.the_cat_api.R
import com.example.the_cat_api.data.model.CatBreed
import com.example.the_cat_api.data.model.CatBreedImage
import com.example.the_cat_api.data.model.CatImage

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.CatImageViewHolder>() {

    private lateinit var onItemClick: (String) -> Unit
    private var catImages: MutableList<CatBreedImage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatImageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_image_item, parent, false)
        return CatImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatImageViewHolder, position: Int) {
        val currentCatImage = catImages[position]
        holder.bind(currentCatImage)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = catImages.size

    fun setCatImages(images: List<CatBreedImage>) {
        catImages.let {
            val startPosition = it.size
            it.addAll(images)
            notifyItemRangeInserted(startPosition, images.size)
        }
    }

    fun clearCatImages() {
        catImages.let {
            val size = it.size
            it.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    fun setOnItemClick(onItemClick: (String) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class CatImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catIdTextView: TextView = itemView.findViewById(R.id.tv_cat_id)
        private val catBreedImageView: ImageView = itemView.findViewById(R.id.iv_cat_breed)
        fun bind(catImage: CatBreedImage) {
            catIdTextView.text = catImage.breeds[0].name
            Glide.with(itemView)
                .load(catImage.url)
                .into(catBreedImageView)
            itemView.setOnClickListener {
                onItemClick(catImage.breeds[0].id)
            }
        }
    }
}