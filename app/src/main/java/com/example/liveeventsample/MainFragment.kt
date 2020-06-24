package com.example.liveeventsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.main_fragment.nextPageButton
import kotlinx.android.synthetic.main.main_fragment.title

class MainFragment : Fragment() {

    private val pageNo: Int by lazy { arguments?.getInt(PAGE_NO) ?: 0 }

    companion object {
        const val TAG = "MainFragment"
        private const val PAGE_NO = "page_no"
        fun newInstance(pageNo: Int) = MainFragment().also {
            it.arguments = Bundle().also { bundle ->
                bundle.putInt(PAGE_NO, pageNo)
            }
        }
    }

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        title.text = "page: $pageNo"

        // 実態がLiveEventなので、値が入っていた時に、observeし直しても通知されない。
        // (重要) 値がsetされたときは通知される。
        viewModel.transitEvent.observe(viewLifecycleOwner) {
            Log.d(TAG, "transit event triggered")
            (activity as? MainActivity)?.transitNextPage(pageNo + 1)
        }

        nextPageButton.setOnClickListener {
            Log.d(TAG, "hasObserver=${viewModel.transitEvent.hasObservers()}")
            viewModel.nextPage()
        }
    }
}
