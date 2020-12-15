package com.example.projectandroid
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import com.example.projectandroid.databinding.GridViewItemBinding
import com.example.projectandroid.databinding.RestaurantsFragmentBinding

class RestaurantsFragment : Fragment() {


    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(this).get(RestaurantsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = RestaurantsFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoAdapter()

        setHasOptionsMenu(true)

        return binding.root

    }

/*
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = of(this).get(RestaurantsViewModel::class.java)

    }*/

}