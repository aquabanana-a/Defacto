package com.banana.defacto.view.factList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.banana.defacto.R
import com.banana.defacto.data.Fact
import com.banana.defacto.databinding.FragmentFactListHistoryItemBinding

class FactListHistoryAdapter(
    private val facts: MutableList<Fact>
) : RecyclerView.Adapter<FactListHistoryAdapter.FactHistoryItem>() {

    private var onItemClick: ((Fact) -> Unit)? = null

    override fun getItemCount(): Int = facts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHistoryItem {
        return FactHistoryItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_fact_list_history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FactHistoryItem, position: Int) {
        val fact = facts[position]
        holder.onBindViewHolder(fact)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(fact)
        }
    }

    fun addFact(fact: Fact) {
        if (facts.indexOfFirst { it.number == fact.number && it.type == fact.type } != -1)
            return

        facts.add(0, fact)
        notifyItemInserted(0)
    }

    fun addFacts(newFacts: List<Fact>) {
        newFacts.forEach { addFact(it) }
    }

    fun setOnItemClickListener(handler: ((Fact) -> Unit)?) {
        onItemClick = handler
    }

    class FactHistoryItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val vb: FragmentFactListHistoryItemBinding =
            FragmentFactListHistoryItemBinding.bind(itemView)

        fun onBindViewHolder(fact: Fact) {
            vb.numberTextView.text = fact.number.toString()
            vb.typeTextView.text = fact.type.toString()
            vb.descriptionTextView.text = fact.description
        }
    }
}