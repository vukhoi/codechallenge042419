package com.example.a20190424vukhoinycschools.model;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NewYorkDataApi {

    @GET("resource/s3k6-pzi2.json")
    Observable<List<School>> getSchoolList();

    @GET("resource/f9bf-2cp4.json")
    Observable<List<AvgScore>> getAvgScoreList();
}
