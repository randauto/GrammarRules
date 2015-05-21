package com.vinilearning.english.grammar;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.vinilearning.english.grammar.MainActivity.CATEGORY;
import com.vinilearning.english.grammar.adapter.GrammarAdapter;
import com.vinilearning.english.grammar.bean.GrammarRule;
import com.vinilearning.english.grammar.common.AppConstants;
import com.vinilearning.english.grammar.utils.JsonUtil;

/**
 * A list fragment representing a list of Items. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link ItemDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks {
		/**
		 * Callback for when an item has been selected.
		 */
		public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}
	};

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemListFragment() {
	}

	private GrammarAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadDataFromJsonFile();
	}

	String titleText = "";

	private void loadDataFromJsonFile() {

		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(JsonUtil.loadJSONFromAsset(
					getActivity(), AppConstants.JSONFILE));
			switch (GrammarApplication.category) {
			case BASIC:
				titleText = AppConstants.ENGLISH_BASIC;
				if (GrammarApplication.arrGrammarBasic == null) {
					GrammarApplication.arrGrammarBasic = new ArrayList<GrammarRule>();
					getDataFromJsonFile(jsonObject);
				}
				adapter = new GrammarAdapter(getActivity(),
						GrammarApplication.arrGrammarBasic);
				GrammarApplication.category = CATEGORY.BASIC;
				break;

			case INTERMEDIATE:
				titleText = AppConstants.ENGLISH_INTERMEDIATE;
				if (GrammarApplication.arrGrammarIntermediate == null) {
					GrammarApplication.arrGrammarIntermediate = new ArrayList<GrammarRule>();
					getDataFromJsonFile(jsonObject);
				}
				adapter = new GrammarAdapter(getActivity(),
						GrammarApplication.arrGrammarIntermediate);
				GrammarApplication.category = CATEGORY.INTERMEDIATE;
				break;

			case ADVANCED:
				titleText = AppConstants.ENGLISH_ADVANCED;
				if (GrammarApplication.arrGrammarAdvanced == null) {
					GrammarApplication.arrGrammarAdvanced = new ArrayList<GrammarRule>();
					getDataFromJsonFile(jsonObject);
				}
				adapter = new GrammarAdapter(getActivity(),
						GrammarApplication.arrGrammarAdvanced);
				GrammarApplication.category = CATEGORY.ADVANCED;
				break;

			default:
				break;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		TextView tvTitle = (TextView) getActivity().findViewById(R.id.tvTitle);
		if (tvTitle != null)
			tvTitle.setText(titleText);

		if (adapter != null) {
			setListAdapter(adapter);
		}
	}

	private void getDataFromJsonFile(JSONObject jsonObject)
			throws JSONException {
		JSONArray jsArray;
		jsArray = jsonObject.getJSONArray(titleText);
		for (int i = 0; i < jsArray.length(); i++) {
			JSONObject jo_inside = jsArray.getJSONObject(i);
			GrammarRule item = new GrammarRule();

			item.setId(i);

			String title = jo_inside.getString("title");
			if (!TextUtils.isEmpty(title)) {
				item.setTitle(title);
			}

			String description = jo_inside.getString("description");
			if (!TextUtils.isEmpty(description)) {
				item.setDescription(jo_inside.getString("description"));
			}
			
			String pathFile = jo_inside.getString("link");
			if(!TextUtils.isEmpty(pathFile)){
				item.setPathFile(pathFile);
			}

			if (titleText.equals("Basic English")) {
				GrammarApplication.arrGrammarBasic.add(item);
			} else if (titleText.equals("Intermediate English")) {
				GrammarApplication.arrGrammarIntermediate.add(item);
			} else if (titleText.equals("Advanced English")) {
				GrammarApplication.arrGrammarAdvanced.add(item);
			}
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.

		if (titleText.equals("Basic English")) {
			mCallbacks.onItemSelected(String
					.valueOf(GrammarApplication.arrGrammarBasic.get(position)
							.getId()));
		} else if (titleText.equals("Intermediate English")) {
			mCallbacks.onItemSelected(String
					.valueOf(GrammarApplication.arrGrammarIntermediate.get(
							position).getId()));
		} else if (titleText.equals("Advanced English")) {
			mCallbacks.onItemSelected(String
					.valueOf(GrammarApplication.arrGrammarAdvanced
							.get(position).getId()));
		}

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}
}
