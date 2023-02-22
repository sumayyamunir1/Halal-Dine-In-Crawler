package com.fyp.halalrestaurants;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ccvb.android.yellowapi.model.ModelComments;
import com.fyp.halalrestaurants.RestaurantListAdapter.OnItemClickListener;
import com.fyp.halalrestaurants.RestaurantListAdapter.ViewHolder;

public class CommentsListAdapter extends BaseAdapter implements OnClickListener {

	private static LayoutInflater inflater = null;
	private Activity activity;
	private ArrayList<ModelComments> data;
	public Resources resourses_reviews;
	ModelComments comments = null;

	public CommentsListAdapter(Activity a, ArrayList<ModelComments> d,
			Resources resLocal) {

		activity = a;
		data = d;
		resourses_reviews = resLocal;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public static class ViewHolder {

		public TextView user_name;
		public TextView user_comment;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder holder;
		if (rowView == null) {
			rowView = inflater.inflate(R.layout.reviews_listview, null);
			holder = new ViewHolder();

			// rowView.setBackgroundColor(getResources().getColor(android.R.color.white));

			holder.user_name = (TextView) rowView.findViewById(R.id.user_name);
			holder.user_comment = (TextView) rowView.findViewById(R.id.user_comment);

			rowView.setTag(holder);
		} else

			holder = (ViewHolder) rowView.getTag();

		if (data.size() <= 0) {
			holder.user_comment.setText("No Data");

		} else {

			comments = (ModelComments) data.get(position);

			holder.user_name.setText(comments.getUserName());
			holder.user_comment.setText(comments.getUserComment());

			
			// rowView.setBackgroundResource(R.drawable.rounded_corner);
			// rowView.setBackgroundResource(R.color.pink);
			rowView.setOnClickListener(new OnItemClickListener(position));
		}

		return rowView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {

			ReviewsActivity sct = (ReviewsActivity) activity;

			// sct.onItemClick(mPosition);
		}
	}
}
