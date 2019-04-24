package com.example.a20190424vukhoinycschools.model;



import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static NewYorkDataApi newYorkDataApi;
    private static Retrofit retrofit;
    private static RetrofitHelper INSTANCE;

    private RetrofitHelper(){
        initializeRetrofit();
    }

    public static RetrofitHelper getINSTANCE(){
        if (INSTANCE == null){
            synchronized (RetrofitHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new RetrofitHelper();
                }
            }
        }
        return INSTANCE;
    }

    public static Observable makeRetrofitCall(String type) {
        switch (type){
            case ("School"):
                return newYorkDataApi.getSchoolList();
            case("AvgScore"):
                return newYorkDataApi.getAvgScoreList();
            default:
        }
        return null;
    }

    private static void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://data.cityofnewyork.us/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newYorkDataApi = retrofit.create(NewYorkDataApi.class);
    }


}
