package com.example.feature_list

import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.api.CharactersApi
import com.example.api.MultiViewModelFactory
import com.example.core.FeatureScope
import dagger.Component
import java.util.*
import javax.inject.Scope
import kotlin.properties.Delegates

@FeatureScope
@Component(dependencies = [CharactersDeps::class])
internal interface FeatureListComponent {

    fun inject(fragment: ListFragment)

    @Component.Builder
    interface Builder {

        fun deps(charactersDeps: CharactersDeps): Builder

        fun build(): FeatureListComponent
    }
}

interface CharactersDeps {

    val multiViewModelFactory : MultiViewModelFactory

    val charactersApi: CharactersApi
}

interface CharactersDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: CharactersDeps

    companion object : CharactersDepsProvider by CharactersDepsStore
}

object CharactersDepsStore : CharactersDepsProvider {

    override var deps: CharactersDeps by Delegates.notNull()
}

internal class FeatureListComponentViewModel : ViewModel() {

    val newComponent =
        DaggerFeatureListComponent.builder().deps(CharactersDepsProvider.deps).build()
}