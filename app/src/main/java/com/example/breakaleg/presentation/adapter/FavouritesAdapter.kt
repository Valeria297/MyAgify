package com.example.breakaleg.presentation.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.breakaleg.R
import com.example.breakaleg.presentation.viewmodel.MainViewModel
import com.example.domain.entities.NameEntity

class FavouritesAdapter(
    val viewModel: MainViewModel,
    val context: Context,
    val btn: Button
) : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    private var mList: List<NameEntity> = emptyList()
    private var checkList = mutableListOf<NameEntity>()
    private var isShowBox = false

    fun setData(list: List<NameEntity>) {
        mList = list
        checkList.clear()
        notifyDataSetChanged()
    }

    fun cleanData() {
        mList = emptyList()
        checkList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favs, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        holder.textView.text = user.name

        holder.checkbox.isChecked = checkList.contains(user)
        if (isShowBox) {
            holder.checkbox.visibility = View.VISIBLE
            btn.visibility = View.VISIBLE
        } else {
            holder.checkbox.visibility = View.GONE
            btn.visibility = View.GONE
        }

        holder.textView.setOnClickListener {
            isShowBox = true
            notifyDataSetChanged()
        }

        holder.checkbox.setOnClickListener {
            if (checkList.isNotEmpty()) {
                btn.visibility = View.VISIBLE
            } else {
                btn.visibility = View.GONE
            }

            if (holder.checkbox.isChecked) {
                btn.visibility = View.VISIBLE
                checkList.add(user)

                btn.setOnClickListener {
                    val builder = AlertDialog.Builder(context)
                    builder.setMessage(
                        "Are you sure that you want to delete?"
                    )

                    builder.setPositiveButton("Yes") { _, _ ->
                        checkList.forEach {
                            viewModel.removeFavoriteName(it)
                        }
                        viewModel.getFavoriteNames()
                        checkList.clear()
                    }

                    builder.setCancelable(true)
                    builder.setNegativeButton("No") { _, _ ->
                        viewModel.getFavoriteNames()
                    }

                    isShowBox = false

                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            } else if (!holder.checkbox.isChecked) {
                checkList.remove(user)
                notifyDataSetChanged()
            }

        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view)
        val checkbox: CheckBox = itemView.findViewById(R.id.checkBox)
    }
}