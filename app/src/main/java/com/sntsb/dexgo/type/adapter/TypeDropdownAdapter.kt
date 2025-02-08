package com.sntsb.dexgo.type.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.sntsb.dexgo.type.enums.TypeEnum
import com.sntsb.dexgo.utils.UiUtils
import java.util.Locale

class TypeDropdownAdapter(
    private val mContext: Context,
    private var values: ArrayList<String>,
    private var textViewResourceId: Int,
) : ArrayAdapter<String>(
    mContext, textViewResourceId, values
) {

    var filtered = ArrayList<String>()
    var original = ArrayList<String>()

    init {
        this.filtered = values
        this.original = values
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView

        label.text = filtered[position]

        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView

        val pokemonType = TypeEnum.valueOf(filtered[position])

        val typeString = UiUtils(mContext).getTipoLabel(pokemonType)

        label.text = typeString
        return label
    }

    override fun add(str: String?) {
        if (str != null) {
            if (!original.contains(str)) {
                original.add(str)
                original.sort()
                notifyDataSetChanged()
            }
        }
    }

    override fun getCount() = filtered.size

    override fun getItem(position: Int) = filtered[position]

    override fun getFilter() = filter

    private var filter: Filter = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            val query =
                if (constraint != null && constraint.isNotEmpty()) autocomplete(constraint.toString())
                else original

            results.values = query
            results.count = query.size

            return results
        }

        private fun autocomplete(input: String): ArrayList<String> {
            val results = arrayListOf<String>()

            for (value in values) {
                if (value.lowercase(Locale.getDefault())
                        .contains(input.lowercase(Locale.getDefault()))
                ) results.add(
                    value
                )
            }

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            filtered = results.values as ArrayList<String>
            notifyDataSetInvalidated()
        }

        override fun convertResultToString(result: Any) = (result as String)
    }
}