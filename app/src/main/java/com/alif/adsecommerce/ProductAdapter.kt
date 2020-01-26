package com.alif.adsecommerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alif.adsecommerce.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val products: List<Product>,
    private val onClickProduct: (title: String, photoUrl: String, photoView :View) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val saleImageView: ImageView = itemView.findViewById(R.id.saleImageView)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.photoUrl).into(holder.image)
        holder.title.text = product.title
        holder.price.text = product.price.toString()

        if(product.isOnSale)
        {
            holder.saleImageView.visibility = View.VISIBLE
        }
        else
        {
            holder.saleImageView.visibility = View.GONE
        }

        holder.image.setOnClickListener {
            onClickProduct.invoke(product.title, product.photoUrl, holder.image)
        }
    }
}