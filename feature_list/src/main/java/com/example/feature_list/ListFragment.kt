package com.example.feature_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.MultiViewModelFactory
import com.example.api.MyCharacter
import dagger.Lazy
import javax.inject.Inject

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var recyclerView: RecyclerView

    @Inject
    internal lateinit var viewModelFactory: Lazy<MultiViewModelFactory>

    private val viewModel: ListViewModel by viewModels { viewModelFactory.get() }
    private val adapter: CharacterAdapter by lazy(LazyThreadSafetyMode.NONE) { CharacterAdapter() }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FeatureListComponentViewModel>()
            .newComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = this.adapter
        viewModel.fetchCharacters()
        viewModel.characters.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            Log.e("onViewCreated", "$it")
        })

        adapter.attachClicks(object : UserListAdapterClicks {
            override fun onItemClick(model: MyCharacter) {
                Log.e("ListFragment","${model.name}")
                findNavController().navigate(
                    R.id.action_listFragment_to_detailFragment,
                    bundleOf("USER_DATA" to model.name)
                )
            }
        })

    }
}