package jaiyou.nfu.chiayitravel.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import jaiyou.nfu.chiayitravel.R


class FragmentBottom : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_bottom, container, false)
    }

    companion object{
        fun newInstance(): FragmentBottom {
            return FragmentBottom()
        }
    }

}
