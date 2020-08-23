package com.sofdigitalhackathon.libertypolls.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.ui.activity.PollCreatingActivity;

import java.util.ArrayList;
import java.util.List;

public class PollCreatorAdapter extends RecyclerView.Adapter<PollCreatorAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<PollCreatingActivity.QuestionItem> allQuestions = new ArrayList<>();

    public PollCreatorAdapter(Context context, List<PollCreatingActivity.QuestionItem> questionItems) {
        this.inflater = LayoutInflater.from(context);
        allQuestions = questionItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.question_item,parent,false);
                item.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PollCreatingActivity.QuestionItem questionItem = allQuestions.get(position);
        View item = holder.getItem();
        TextInputLayout tiTitle = item.findViewById(R.id.question_item_title);
        TextInputLayout tiDesctiption = item.findViewById(R.id.question_item_decription);


//        tvNum.setText("Вопрос №" + position+1);
        tiTitle.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionItem.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiDesctiption.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                questionItem.setDescription(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return allQuestions.size();
    }

    public void addItem(PollCreatingActivity.QuestionItem item){
        allQuestions.add(item);
        notifyDataSetChanged();
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
