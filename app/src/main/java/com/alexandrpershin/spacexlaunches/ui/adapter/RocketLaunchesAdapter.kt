package com.alexandrpershin.spacexlaunches.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexandrpershin.spacexlaunches.R
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity
import com.alexandrpershin.spacexlaunches.model.RocketLaunchUtils
import com.alexandrpershin.spacexlaunches.utils.DateUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RocketLaunchesAdapter(
    private val context: Context,
    private var items: List<RocketLaunchDbEntity> = arrayListOf()
) :
    RecyclerView.Adapter<RocketLaunchesAdapter.RocketLaunchesViewHolder>() {

    var onItemClick: ((Long) -> Unit)? = null

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketLaunchesViewHolder {
        val view = layoutInflater.inflate(R.layout.item_rocket_launch, parent, false)
        return RocketLaunchesViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RocketLaunchesViewHolder, position: Int) {
        holder.bind(position)
    }

    fun submitData(newData: List<RocketLaunchDbEntity>) {
        items = newData

        notifyDataSetChanged()
    }

    inner class RocketLaunchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val missionIdTv = itemView.findViewById<TextView>(R.id.missionIdTv)
        private val missionIconIv = itemView.findViewById<ImageView>(R.id.missionIconIv)
        private val missionNameTv = itemView.findViewById<TextView>(R.id.missionNameTv)
        private val launchDateTv = itemView.findViewById<TextView>(R.id.launchDateTv)

        fun bind(position: Int) {
            val rocketLaunch = items[position]

            missionIdTv.text = RocketLaunchUtils.getMissionId(rocketLaunch.missionId)
            missionNameTv.text = rocketLaunch.missionName
            launchDateTv.text = DateUtils.unixToStringDate(context, rocketLaunch.launchDateUnix)

            Glide.with(context)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_jetpack_rocket))
                .load(rocketLaunch.imageUrl).into(missionIconIv)

            itemView.setOnClickListener {
                onItemClick?.invoke(rocketLaunch.id)
            }

        }

    }
}