package com.vinilearning.english.grammar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vinilearning.english.grammar.bean.GrammarRule;

/**
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private GrammarRule mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			switch (GrammarApplication.category) {
			case BASIC:
				mItem = GrammarApplication.arrGrammarBasic.get(Integer
						.parseInt(getArguments().getString(ARG_ITEM_ID)));
				break;

			case INTERMEDIATE:
				mItem = GrammarApplication.arrGrammarIntermediate.get(Integer
						.parseInt(getArguments().getString(ARG_ITEM_ID)));
				break;

			case ADVANCED:
				mItem = GrammarApplication.arrGrammarAdvanced.get(Integer
						.parseInt(getArguments().getString(ARG_ITEM_ID)));
				break;

			default:
				break;
			}

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);

		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem
					.getTitle());
		}

		return rootView;
	}
}
