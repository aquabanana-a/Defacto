package com.banana.defacto.view.factList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.banana.defacto.R
import com.banana.defacto.data.Fact
import com.banana.defacto.data.NumberType
import com.banana.defacto.databinding.FragmentFactListBinding
import com.banana.defacto.di.AppGraph
import javax.inject.Inject

class FactListFragment : Fragment(), FactListContract.View, FactListContract.Router {
    @Inject
    lateinit var presenter: FactListPresenterImpl

    private lateinit var vb: FragmentFactListBinding
    private lateinit var historyAdapter: FactListHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AppGraph.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFactListBinding.inflate(inflater, container, false)
            .apply { vb = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.attachRouter(this)
        lifecycle.addObserver(presenter)

        vb.numberTypeSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            NumberType.entries.map { it.name }
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        vb.numberTypeSpinner.setSelection(NumberType.entries.indexOf(NumberType.Trivia))

        vb.numberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            private var isUpdatingText = false

            override fun afterTextChanged(s: Editable?) {
                if (isUpdatingText) return

                val input = s.toString()
                if (input.isEmpty()) return

                val numberLong = input.toLongOrNull() ?: return

                var correctedNumber = numberLong
                if (numberLong > Int.MAX_VALUE) correctedNumber = Int.MAX_VALUE.toLong()
                if (numberLong < Int.MIN_VALUE) correctedNumber = Int.MIN_VALUE.toLong()

                if (correctedNumber.toString() != input) {
                    isUpdatingText = true
                    vb.numberEditText.setText(correctedNumber.toString())
                    vb.numberEditText.setSelection(vb.numberEditText.text.length)
                    isUpdatingText = false
                }
            }
        })

        vb.getFactButton.setOnClickListener {
            val enteredText = vb.numberEditText.text.toString()
            val number = enteredText.toIntOrNull()

            if (enteredText.isNotEmpty() && number != null) {
                presenter.handleGetFactClick(number, getSelectedNumberType())
            } else {
                Toast.makeText(requireContext(), R.string.fact_list_enter_number_incorrect_warn, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        vb.getFactRandomButton.setOnClickListener {
            presenter.handleGetRandomFactClick(getSelectedNumberType())
        }

        historyAdapter = FactListHistoryAdapter(arrayListOf())
        historyAdapter.setOnItemClickListener {
            presenter.handleHistoryFactClick(it)
        }

        vb.factsHistoryRecyclerView.setAdapter(historyAdapter)
        vb.factsHistoryRecyclerView.setLayoutManager(LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false))
    }

    override fun updateFactHistory(facts: List<Fact>) {
        historyAdapter.addFacts(facts)
        vb.factsHistoryEmptyTextView.isVisible = historyAdapter.getItemCount() == 0
    }

    private fun getSelectedNumberType(): NumberType {
        return NumberType.entries.getOrNull(vb.numberTypeSpinner.selectedItemPosition)
            ?: NumberType.Trivia
    }

    override fun openFactDetailsFragment(fact: Fact) {
        val action = FactListFragmentDirections.actionFactListFragmentToFactDetailsFragment(fact)
        findNavController().navigate(action)
    }

    override fun showLoading(visible: Boolean) {
        vb.loadingView.isVisible = visible
    }

    override fun showSomethingWentWrongAlert() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.fact_list_error_alert_title)
            .setMessage(R.string.fact_list_error_alert_text)
            .setPositiveButton(R.string.fact_list_error_alert_btn_ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        presenter.detachRouter()
    }
}