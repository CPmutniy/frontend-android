package com.sofdigitalhackathon.libertypolls.network;

import android.content.Context;
import android.util.Base64;
import android.widget.AutoCompleteTextView;

import com.sofdigitalhackathon.libertypolls.data.Constants;
import com.sofdigitalhackathon.libertypolls.hepler.NetworkUtils;
import com.sofdigitalhackathon.libertypolls.hepler.StringHelper;
import com.sofdigitalhackathon.libertypolls.model.Building;
import com.sofdigitalhackathon.libertypolls.model.Company;
import com.sofdigitalhackathon.libertypolls.model.Flat;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.model.User;

import java.security.PublicKey;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
    Interface for retrofit
 */
public class PollApi {
    PollApiInterface apiHelper;
    Context context;
    String AuthorizationKey = "";

    public PollApi(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiHelper = retrofit.create(PollApiInterface.class);
        //AuthorizationKey = context.getSharedPreferences(Constants.APP_PREF_NAME, Context.MODE_PRIVATE).getString(Constants.APP_API_ACCESS_KEY, "");
    }
    public Observable<List<Company>> GetCompany(int id) {
        return apiHelper.getCompanies(AuthorizationKey);
    }


    public Observable<List<Building>> GetBuildings() {
        return apiHelper.getBuildings(AuthorizationKey);
    }
    public Observable<ResponseBody> CreateUser(int flatId, String key) {                            // base64 encoded key
        //String encodedKey = new String(Base64.encode(key.getEncoded(),Base64.DEFAULT));

        return apiHelper.createUsers(AuthorizationKey,
                StringHelper.generate(8),
                StringHelper.generate(8),
                StringHelper.generate(8),
                flatId,
                false,
                key);
    }
    public Observable<List<Flat>> GetFlats() {
        return apiHelper.getFlats(AuthorizationKey);
    }

    public Observable<List<User>> GetUsers() {
        return apiHelper.getUsers(AuthorizationKey);
    }

    public Observable<List<Poll>> GetPolls() {
        return apiHelper.getPolls(AuthorizationKey);
    }

    public Observable<Flat> GetFlat(int id) {
        return apiHelper.getFlat(AuthorizationKey,id);
    }

    public Observable<Question> GetQuestion(int id) {
        return apiHelper.getQuestion(AuthorizationKey,id);
    }
    public Observable<ResponseBody> CreateAnswer(int user, int question, String time, String answer) {
        String signature = " SIGN HERE WITH PUCLIC KEY";
        return apiHelper.createAnswer(AuthorizationKey,user,question,time,answer,signature);
    }
    public Observable<Poll> CreatePoll(String title,int building,int user){
        return apiHelper.createPoll(AuthorizationKey,title,building,user);
    }
    public Observable<Question> CreateQuestion(String title,String desc,int pollId){
        return apiHelper.createQuestion(AuthorizationKey,title,desc,pollId);
    }
}
