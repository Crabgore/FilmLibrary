package com.geekbrains.team.filmlibrary.fragments.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.OneItemAdapter
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoFragmentBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_screen_fragment.*
import kotlinx.android.synthetic.main.pager_indicator_item.*
import javax.inject.Inject

class FullFilmInfoFragment : DaggerFragment() {
    private val args: FullFilmInfoFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FullFilmInfoViewModel>({ activity as MainActivity }) { viewModelFactory }
    lateinit var binding: FullFilmInfoFragmentBinding
    private val infoAdapter by lazy {
        OneItemAdapter<MovieView>(layout = R.layout.full_film_info_item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.full_film_info_fragment, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startObservers()
        loadMovieDetails()
        showMovieDetails()
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { msg ->
            Toast.makeText(context, msg.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                infoAdapter.update(it)
                startIndicators()
                setCurrentIndicator(0)
                binding.movie = it
            }
        })
    }

    private fun loadMovieDetails() {
        viewModel.loadMovieInfo(args.id)
    }

    private fun showMovieDetails() {
        topPager.apply {
            adapter = infoAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })
        }
    }

    private fun startIndicators() {
        indicator.removeAllViews()
        val indicators = arrayOfNulls<ImageView>(infoAdapter.itemCount)

        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.indicator_inactive
                    )
                }
            )
            indicators[i]?.layoutParams = indicator_item.layoutParams
            indicator.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount: Int = indicator.childCount
        for (i in 0 until childCount) {
            val imageView =
                indicator.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_active
                        )
                    }
                )
            } else {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_inactive
                        )
                    }
                )
            }
        }
    }
}