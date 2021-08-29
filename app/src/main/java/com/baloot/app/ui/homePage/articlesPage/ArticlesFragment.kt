package com.baloot.app.ui.homePage.articlesPage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.baloot.app.R
import com.baloot.app.databinding.FragmentArticlesBinding
import com.baloot.app.di.DaggerAppComponent
import com.baloot.app.ui.homePage.adapter.ArticlesAdapter
import com.baloot.app.ui.homePage.articlesPage.viewModel.ArticleViewModel
import com.baloot.app.ui.homePage.articlesPage.viewModel.ArticleViewModelImpl
import com.baloot.app.util.FooterAdapterVertical
import com.core.base.ParentFragment
import com.core.repository.HomeRepository
import com.core.repository.LocalRepository
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class ArticlesFragment : ParentFragment<ArticleViewModel, FragmentArticlesBinding>() {

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var homeRepository: HomeRepository

    private val articlesAdapter: ArticlesAdapter by lazy {
        ArticlesAdapter {
            Toasty.success(
                requireContext(),
                "adapter test with paging ${it.title}",
                Toast.LENGTH_SHORT,
                true
            ).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initArticleRecycler()
        subscribeArticleItems()

    }

    private fun initArticleRecycler() {
        dataBinding.articleRv.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter =
                articlesAdapter.withLoadStateFooter(FooterAdapterVertical { articlesAdapter.retry() })
        }
    }

    private fun subscribeArticleItems() {
        lifecycleScope.launch {
            viewModel.getArticleData().collectLatest { bestPagingData ->
                articlesAdapter.submitData(bestPagingData)
            }
        }
        articlesAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {

                    dataBinding.articleRv.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {

                    dataBinding.articleRv.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error

                    Toast.makeText(
                        requireActivity(),
                        "Load Error: ${state.error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    override fun getViewModelClass(): Class<ArticleViewModel> = ArticleViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ArticleViewModelImpl(
                    application = requireActivity().application,
                    localRepository = localRepository,
                    homeRepository = homeRepository
                ) as T
            }
        }
    }

    override fun getResourceLayoutId(): Int = R.layout.fragment_articles

    override fun inject() {
        DaggerAppComponent.builder()
            .app(requireActivity().application)
            .build()
            .inject(this)
    }


}