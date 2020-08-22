package com.sofdigitalhackathon.libertypolls.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.sofdigitalhackathon.libertypolls.R;

public class PollItem extends LinearLayout {
    private TextView tvTitile;
    private TextView tvInitiator;
    private TextView tvTime;

    public PollItem(Context context) {
        super(context);
        Init(context,null);
    }

    public PollItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(context,attrs);

    }

    private void Init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.component_poll_item, this);
        InitReferences();
    }

    private void InitReferences() {
        tvTitile = findViewById(R.id.poll_item_title);
        tvInitiator = findViewById(R.id.poll_item_initiator);
        tvTime = findViewById(R.id.poll_item_time);
    }

    public void setTitle(String title){
        tvTitile.setText(title);
    }
    public void setTime(String time){
        tvTime.setText(time);
    }
    public void setInitiator(String initiator){
        tvInitiator.setText(initiator);
    }


    public interface OnPollItemInteract{
        public void onClick(PollItem pollItem);
    }
}
