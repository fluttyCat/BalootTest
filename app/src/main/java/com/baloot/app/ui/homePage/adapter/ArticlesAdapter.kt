package com.baloot.app.ui.homePage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.baloot.app.databinding.ItemArticlePreviewBinding
import com.core.base.adapter.BaseHolder
import com.core.dto.article.ArticleDto

class ArticlesAdapter(private val itemClickCallback: (ArticleDto) -> Unit) :
    PagingDataAdapter<ArticleDto, BaseHolder<ArticleDto>>(object :
        DiffUtil.ItemCallback<ArticleDto>() {

        override fun areItemsTheSame(
            oldItem: ArticleDto,
            newItem: ArticleDto
        ): Boolean {
            return oldItem.author == newItem.author
        }

        override fun areContentsTheSame(
            oldItem: ArticleDto,
            newItem: ArticleDto
        ): Boolean {
            return oldItem == newItem
        }

    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<ArticleDto> {

        return ArticleViewHolder(
            ItemArticlePreviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: BaseHolder<ArticleDto>, position: Int) {
        holder.bind(getItem(position)!!, position)
    }


    inner class ArticleViewHolder(
        private val binding: ItemArticlePreviewBinding
    ) : BaseHolder<ArticleDto>(binding) {

        override fun bind(value: ArticleDto, position: Int) {
            binding.articleItem = value

            binding.root.setOnClickListener {
                itemClickCallback(value)
            }


        }


    }

}
