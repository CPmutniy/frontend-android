package com.sofdigitalhackathon.libertypolls.network;

import com.sofdigitalhackathon.libertypolls.model.Answer;
import com.sofdigitalhackathon.libertypolls.model.Building;
import com.sofdigitalhackathon.libertypolls.model.Company;
import com.sofdigitalhackathon.libertypolls.model.Flat;
import com.sofdigitalhackathon.libertypolls.model.Poll;
import com.sofdigitalhackathon.libertypolls.model.Question;
import com.sofdigitalhackathon.libertypolls.model.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/*
    Interface for retrofit
 */
public interface PollApiInterface {
    @GET("/person/")
    Observable<List<User>> getUsers(@Header("Authorisation") String key);

    @FormUrlEncoded
    @POST("/person/")
    Observable<ResponseBody> createUsers(@Header("Authorisation") String key,
                                         @Field("name") String name,
                                         @Field("surname") String surname,
                                         @Field("patronymic") String patronymic,
                                         @Field("flat") int flatId,
                                         @Field("state") boolean state,
                                         @Field("publick_key") String publicKey);
    @GET("/company/")
    Observable<List<Company>> getCompanies(@Header("Authorisation") String key);

    @GET("/adress/")
    Observable<List<Building>> getBuildings(@Header("Authorisation") String key);

    @GET("/voting/")
    Observable<List<Poll>> getPolls(@Header("Authorisation") String key);

    @FormUrlEncoded
    @POST("/voting/")
    Observable<Poll> createPoll(@Header("Authorisation") String key,
                                @Field("name") String name,
                                @Field("adress") int building,
                                @Field("initiator") int user);

    @GET("/question/")
    Observable<List<Question>> getQuestions(@Header("Authorisation") String key);
    @FormUrlEncoded
    @POST("/question/")
    Observable<Question> createQuestion(@Header("Authorisation") String key,
                                @Field("name") String name,
                                @Field("description") String desc,
                                @Field("voting") int pollId);
    @GET("/answer/")
    Observable<List<Answer>> getAnswers(@Header("Authorisation") String key);

    @GET("/flat/")
    Observable<List<Flat>> getFlats(@Header("Authorisation") String key);

    @GET("/flat/{id}/")
    Observable<Flat> getFlat(@Header("Authorisation") String authorizationKey,@Path("id") int id);
    @GET("/question/{id}/")
    Observable<Question> getQuestion(@Header("Authorisation")String authorizationKey, @Path("id") int id);

    @FormUrlEncoded
    @POST("/answer/")
    Observable<ResponseBody> createAnswer(@Header("Authorisation") String key,
                                          @Field("person") int userId,
                                          @Field("question") int questionId,
                                          @Field("time") String time,
                                          @Field("answer") String answer,
                                          @Field("signature") String signature);
    @GET("/person/{id}/")
    Observable<User> getUser(@Header("Authorisation") String authorizationKey,@Path("id") int id);
}
