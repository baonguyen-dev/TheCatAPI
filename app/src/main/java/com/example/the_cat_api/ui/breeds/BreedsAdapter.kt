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

    private var catImages: List<CatBreedImage> = emptyList()

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
        catImages = images
        notifyDataSetChanged()
    }

    inner class CatImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catIdTextView: TextView = itemView.findViewById(R.id.tv_cat_id)
        private val catBreedImageView: ImageView = itemView.findViewById(R.id.iv_cat_breed)
        fun bind(catImage: CatBreedImage) {
            catIdTextView.text = catImage.breeds[0].name
            Glide.with(itemView)
                .load(catImage.url)
                .into(catBreedImageView)
        }
    }
}