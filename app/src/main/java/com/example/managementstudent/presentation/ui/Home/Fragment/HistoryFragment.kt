package com.example.managementstudent.presentation.ui.Home.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managementstudent.R
import com.example.managementstudent.data.model.Grade
import com.example.managementstudent.databinding.FragmentHistoryBinding
import com.example.managementstudent.presentation.ui.Adapter.ClassAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var viewBinding : FragmentHistoryBinding
    val adapterClass : ClassAdapter by lazy {
        ClassAdapter({
            Toast.makeText(activity,"${it.nameGrade}", Toast.LENGTH_SHORT).show()
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentHistoryBinding.inflate(layoutInflater)
        val mListClass = mutableListOf<Grade>()
        for(i in 6 .. 9) {
            mListClass.add(Grade(System.currentTimeMillis().toString(),"Grade $i", 25,"2023"))
        }
        adapterClass.diffUtil.submitList(mListClass)
        viewBinding.rcvClass.apply {
            hasFixedSize()
            adapter = adapterClass
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
        return viewBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}