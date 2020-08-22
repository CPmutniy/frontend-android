package com.sofdigitalhackathon.libertypolls.adapters;

import android.content.Context;
import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.ui.view.PollItem;

import java.util.ArrayList;
import java.util.List;

public class PollItemAdapter extends RecyclerView.Adapter<PollItemAdapter.ViewHolder> {
    final String TAG = "MeetingsListAdapter";
    LayoutInflater inflater;
    List<Poll> allPolls = new ArrayList<>();
    private Poll.OnPollInteract pollItemEvent;

    public PollItemAdapter(Context context, List<Poll> allMeetings) {
        this.inflater = LayoutInflater.from(context);
        allPolls = allMeetings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PollItem pollItem = new PollItem(parent.getContext());
        pollItem.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ViewHolder(pollItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poll currentPoll = allPolls.get(position);
        User intiator = currentPoll.getInitiator();
        PollItem item = holder.getPollItem();
        item.setTitle(currentPoll.getTitle());
        item.setInitiator(intiator.getName() + " " + intiator.getSurname());
        item.setTime(currentPoll.getTime());

        item.setOnClickListener(view -> {
            if (pollItemEvent != null) {
                pollItemEvent.onClick(currentPoll);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPolls.size();
    }

   public void setOnClickListener(Poll.OnPollInteract onClick) {
       this.pollItemEvent = onClick;
   }


    class ViewHolder extends RecyclerView.ViewHolder {
        PollItem item;

        ViewHolder(View view) {
            super(view);
            item = (PollItem) view;
        }

        public PollItem getPollItem() {
            return item;
        }
    }
}
