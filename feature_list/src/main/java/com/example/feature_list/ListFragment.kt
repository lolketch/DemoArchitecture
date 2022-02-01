package com.example.feature_list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.api.MultiViewModelFactory
import dagger.Lazy
import javax.inject.Inject

class ListFragment : Fragment(R.layout.fragment_list) {

    @Inject
    internal lateinit var viewModelFactory: Lazy<MultiViewModelFactory>

    private val viewModel: ListViewModel by viewModels {
        viewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FeatureListComponentViewModel>()
            .newComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}