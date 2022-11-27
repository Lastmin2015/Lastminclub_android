package ru.vsibi.presentation.screens.photoViewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import ru.vsibi.presentation.R
import ru.vsibi.presentation.helpers.Lastmin.getImageUrl


class PhotoViewerAdapter () : PagerAdapter() {

    private val models = mutableListOf<String>()
    private lateinit var layIn : LayoutInflater
    private var refresh = false

    fun setupAdapter(models: List<String>){
        this.models.clear()
        this.models.addAll(models)
        notifyDataSetChanged()
    }

    fun delete(index: Int){
        this.models.removeAt(index)
        refresh = true;
        notifyDataSetChanged()
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0.equals(p1)
    }

    override fun getCount(): Int {
        return models.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layIn = LayoutInflater.from(container.context)
        val view : View = layIn.inflate(R.layout.cell_photo_viewer, container, false)
        val ivPhoto : ImageView = view.findViewById(R.id.iv_photo)
        Glide.with(container.context).load(getImageUrl(models[position])).into(ivPhoto)
        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as RelativeLayout)
    }

    override fun getItemPosition(`object`: Any): Int {
        return if (refresh) {
            refresh = false
            POSITION_NONE
        } else {
            super.getItemPosition(`object`)
        }
    }
}