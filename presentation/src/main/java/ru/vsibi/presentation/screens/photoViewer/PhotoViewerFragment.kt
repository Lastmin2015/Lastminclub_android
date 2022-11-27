package ru.vsibi.presentation.screens.photoViewer

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentPhotoViewerBinding

class PhotoViewerFragment :
    BaseFragment<FragmentPhotoViewerBinding>(FragmentPhotoViewerBinding::inflate, R.layout.fragment_photo_viewer) {

    private val adapter = PhotoViewerAdapter()
    private val args: PhotoViewerFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun FragmentPhotoViewerBinding.initViews() {
        viewpager.configure()
    }

    override fun FragmentPhotoViewerBinding.initListeners() {
        ivBack.setOnClickListener {
            popBack()
        }
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (adapter.count > 0)
                    tvStep.text = "${position + 1} / ${adapter.count}"
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    override fun initData() {
        adapter.setupAdapter(args.photos.toList())
        if (adapter.count > 0)
            binding.tvStep.text = "1 / ${adapter.count}"
    }

    private fun ViewPager.configure() {
        adapter = this@PhotoViewerFragment.adapter
    }
}

