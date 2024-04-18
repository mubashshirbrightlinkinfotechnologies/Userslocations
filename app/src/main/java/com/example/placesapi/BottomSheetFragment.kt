package com.example.placesapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placesapi.adapters.UserLocationAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// BottomSheetFragment.kt
class BottomSheetFragment : BottomSheetDialogFragment() {
    companion object {
        private const val USER_NAMES_KEY = "userNames"

        fun newInstance(userNames: List<String>): BottomSheetFragment {
            val fragment = BottomSheetFragment()
            val args = Bundle()
            args.putStringArrayList(USER_NAMES_KEY, ArrayList(userNames))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
