package com.sofdigitalhackathon.libertypolls.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sofdigitalhackathon.libertypolls.R;
import com.sofdigitalhackathon.libertypolls.adapters.PollItemAdapter;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.model.User;
import com.sofdigitalhackathon.libertypolls.ui.activity.PollInformationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PollMainFragment extends Fragment {

    RecyclerView recyclerView;
    List<Poll> pollList = new ArrayList<>();

    public PollMainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "Poll", Toast.LENGTH_LONG).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitReferences();
        Init();
    }

    private void InitReferences() {
        recyclerView = getView().findViewById(R.id.poll_main_recycleview);
    }

    private void Init() {
        String[] names = {"Петр", "Владислав", "Николай", "ООО ЗамСтрой", "Великолепный"};
        String[] surnames = {"Николаевич", "Петрухин", "Сидоров", "", "Могущий"};
        String[] time = {"Начнеться завтра", "Начнеться через 4 дня", "Осталось 5 дней", "Осталось 2 часа",};
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] descArray = getResources().getStringArray(R.array.description);

        for (int i = 0; i < 15; i++) {
            Poll poll = new Poll();
            poll.setTitle(titles[i % titles.length]);
            poll.setTime(time[i % time.length]);
            poll.setDescription(getResources().getString(R.string.poll_describing));
            poll.setInitiator(new User(names[i % names.length], surnames[i % surnames.length]));
            List<Question> questions = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                Question q = new Question();
                q.setTitle(titles[j % titles.length]);
                q.setDescription(descArray[j % descArray.length]);
                questions.add(q);
            }
            poll.setQuestionList(questions);

            pollList.add(poll);
        }
        PollItemAdapter adapter = new PollItemAdapter(getContext(), pollList);
        adapter.setOnClickListener(poll -> {
            Intent intent = new Intent(getContext(), PollInformationActivity.class);
            intent.putExtra("poll", new Gson().toJson(poll));
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }
}
