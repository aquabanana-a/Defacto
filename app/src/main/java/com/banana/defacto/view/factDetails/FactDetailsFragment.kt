package com.banana.defacto.view.factDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.banana.defacto.data.Fact
import com.banana.defacto.databinding.FragmentFactDetailsBinding
import com.banana.defacto.di.AppGraph
import javax.inject.Inject

class FactDetailsFragment : Fragment(), FactDetailsContract.View {
    @Inject
    lateinit var presenter: FactDetailsPresenterImpl

    private lateinit var vb: FragmentFactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppGraph.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFactDetailsBinding.inflate(inflater, container, false)
            .apply { vb = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this, FactDetailsFragmentArgs.fromBundle(requireArguments()).fact)
    }

    override fun showFact(fact: Fact) {
        vb.numberTextView.text = fact.number.toString()
        vb.typeTextView.text = fact.type.toString()
        vb.descriptionTextView.text = fact.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}