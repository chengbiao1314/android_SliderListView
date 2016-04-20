package com.chengbiao.ricky.sliderlistview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private static final String TAG = "MainActivity";

	private SilderListView mListView;

	private List<MessageItem> mMessageItems = new ArrayList<MessageItem>();

	private SliderView mLastSlideViewWithStatusOn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mListView = (SilderListView) findViewById(R.id.slv_sliderlistview);

		for (int i = 0; i < 20; i++) {
			MessageItem item = new MessageItem();
			if (i % 3 == 0) {
				item.iconRes = R.drawable.default_qq_avatar;
				item.title = i + " >> this is first title";
				item.msg = "this is first message ";
				item.time = "2014-7-22";
			} else {
				item.iconRes = R.drawable.wechat_icon;
				item.title = i + " >> this is secend title";
				item.msg = "this is secend message ";
				item.time = "2014-8-22";
			}
			mMessageItems.add(item);
		}
		mListView.setAdapter(new SlideAdapter());
		mListView.setOnItemClickListener(this);
	}

	private class SlideAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		SlideAdapter() {
			super();
			mInflater = getLayoutInflater();
		}

		@Override
		public int getCount() {
			return mMessageItems.size();
		}

		@Override
		public Object getItem(int position) {
			return mMessageItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			SliderView slideView = (SliderView) convertView;
			if (slideView == null) {
				View itemView = mInflater.inflate(R.layout.list_item, null);

				slideView = new SliderView(MainActivity.this);
				slideView.setContentView(itemView);
				holder = new ViewHolder(slideView);
				slideView.setTag(holder);
			} else {
				holder = (ViewHolder) slideView.getTag();
			}
			MessageItem item = mMessageItems.get(position);
			slideView.shrink();
			holder.icon.setImageResource(item.iconRes);
			holder.title.setText(item.title);
			holder.msg.setText(item.msg);
			holder.time.setText(item.time);
			holder.deleteHolder.setOnClickListener(MainActivity.this);

			return slideView;
		}

	}

	public class MessageItem {
		public int iconRes;
		public String title;
		public String msg;
		public String time;
	}

	private static class ViewHolder {
		public ImageView icon;
		public TextView title;
		public TextView msg;
		public TextView time;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.icon);
			title = (TextView) view.findViewById(R.id.title);
			msg = (TextView) view.findViewById(R.id.msg);
			time = (TextView) view.findViewById(R.id.time);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// Log.e(TAG, "onItemClick position=" + position);

	}

	// @Override
	// public void onSlide(View view, int status) {
	// if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn !=
	// view) {
	// mLastSlideViewWithStatusOn.shrink();
	// }
	//
	// if (status == SLIDE_STATUS_ON) {
	// mLastSlideViewWithStatusOn = (SlideView) view;
	//
	// }
	// }

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.holder) {
			Log.e(TAG, "onClick v=" + v);
//			Toast.makeText(this, v + "",0).show();

		}
	}
}
