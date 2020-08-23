package com.sofdigitalhackathon.libertypolls.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.model.Notification;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.ui.view.PollItem;

import java.util.ArrayList;
import java.util.List;

public class NotificationItemAdapter extends RecyclerView.Adapter<NotificationItemAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Notification> allNotification = new ArrayList<>();
    private Poll.OnPollInteract pollItemEvent;

    public NotificationItemAdapter(Context context, List<Notification> notificationList) {
        this.inflater = LayoutInflater.from(context);
        this.allNotification = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.component_notification_item,parent,false);
                item.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = allNotification.get(position);
        View item = holder.getItem();
        TextView tvTitle =item.findViewById(R.id.notification_item_title);
        TextView tvDesctiption =item.findViewById(R.id.notification_item_description);
        ImageView ivImage =item.findViewById(R.id.notification_item_image);
        tvTitle.setText(notification.getTitle());
        tvDesctiption.setText(notification.getDescription());
        switch (notification.getType()){
            case Notification.TYPE_IMPORTANT:  ivImage.setImageResource(R.drawable.ic_important);break;
            case Notification.TYPE_INDICATIONS:ivImage.setImageResource(R.drawable.ic_indicators); break;
            case Notification.TYPE_REPAIR: ivImage.setImageResource(R.drawable.ic_repare);break;
        }
    }

    @Override
    public int getItemCount() {
        return allNotification.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        View item;

        ViewHolder(View view) {
            super(view);
            item =  view;
        }

        public View getItem() {
            return item;
        }
    }
}
