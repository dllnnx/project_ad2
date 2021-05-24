package com.example.project_ad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.project_ad.models.MyScores;

import java.util.ArrayList;

public class MyScoreAdapter extends ArrayAdapter<MyScores> {

	public MyScoreAdapter(Context context, ArrayList<MyScores> arr) {
		super(context, R.layout.adapter_item, arr);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final MyScores score = getItem(position);

		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
		}

		// Заполняем адаптер
		((TextView) convertView.findViewById(R.id.number)).setText(String.valueOf(score.getId()));
		((TextView) convertView.findViewById(R.id.date)).setText(score.getDate());
		((TextView) convertView.findViewById(R.id.score)).setText(String.valueOf(score.getScore()));

		return convertView;
	}
}
