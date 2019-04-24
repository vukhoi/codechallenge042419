package com.example.a20190424vukhoinycschools.presenter;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;


import com.example.a20190424vukhoinycschools.model.AvgScore;
import com.example.a20190424vukhoinycschools.model.RetrofitHelper;
import com.example.a20190424vukhoinycschools.model.School;
import com.example.a20190424vukhoinycschools.model.SchoolEntry;
import com.example.a20190424vukhoinycschools.view.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenter {
    private final RetrofitHelper retrofitHelper;
    private List<School> schoolList = new ArrayList<>();
    private List<AvgScore> avgScoreList = new ArrayList<>();
    private Observable<List<School>> schoolListObservable;
    private Observable<List<AvgScore>> avgScoreListObservable;
    private final String TAG = this.getClass().getSimpleName();


    public MainActivityPresenter() {
        retrofitHelper = RetrofitHelper.getINSTANCE();
    }


    public void populateRecyclerView(final RecyclerView recyclerView, final SearchView searchView) {
        schoolListObservable = retrofitHelper.makeRetrofitCall("School");
        avgScoreListObservable = retrofitHelper.makeRetrofitCall("AvgScore");

        getSchoolData();
        getAvgScoreData();


        schoolListObservable.zipWith(avgScoreListObservable, new BiFunction<List<School>, List<AvgScore>, List<SchoolEntry>>() {
            @Override
            public List<SchoolEntry> apply(List<School> schoolList, List<AvgScore> avgScoreList) throws Exception {
                List<SchoolEntry> schoolEntryList = new ArrayList<>();

                for (School school : schoolList) {
                    String schoolDbn = school.getDbn();
                    for (AvgScore avgScore : avgScoreList) {
                        if (avgScore.getDbn().equals(schoolDbn)) {
                            SchoolEntry entry = new SchoolEntry(school.getSchoolName(),
                                    school.getSchoolEmail(),
                                    "Average Math Score = " + avgScore.getSatMathAvgScore(),
                                    "Average Reading Score = " +avgScore.getSatCriticalReadingAvgScore(),
                                    "Average Writing Score = " +avgScore.getSatWritingAvgScore(),
                                    avgScore.getNumOfSatTestTakers());
                            schoolEntryList.add(entry);
                            Log.d(this.getClass().getSimpleName(), entry.toString());
                            break;
                        }
                    }
                }
                return schoolEntryList;
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<SchoolEntry>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<SchoolEntry> schoolEntryList) {
                setUpRecyclerView(recyclerView, schoolEntryList);
                setUpSearchView(searchView, recyclerView, schoolEntryList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private  void setUpSearchView(SearchView searchView, final RecyclerView recyclerView, final List<SchoolEntry> schoolEntryList){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<SchoolEntry> schoolEntries = new ArrayList<>();
                for (SchoolEntry schoolEntry : schoolEntryList) {
                    if (schoolEntry.getName().toLowerCase().contains(s.toLowerCase())) {
                        schoolEntries.add(schoolEntry);
                    }
                }
                setUpRecyclerView(recyclerView, schoolEntries);
                return true;
            }
        });
    }

    private void setUpRecyclerView(RecyclerView recyclerView, List<SchoolEntry> schoolEntryList) {
        CustomAdapter customAdapter = new CustomAdapter(schoolEntryList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());

        Log.d(this.getClass().getSimpleName(), "set up recycler view " + schoolEntryList.size());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getSchoolData() {
        schoolListObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<School>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe school");
                    }

                    @Override
                    public void onNext(List<School> schools) {
                        Log.d(TAG, "onNext school");
                        schoolList = schools;
                        for (School school : schoolList) {
                            Log.d(TAG, school.getSchoolName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError school");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete school");
                    }
                });
    }

    private void getAvgScoreData() {
        avgScoreListObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AvgScore>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe avgScore");
                    }

                    @Override
                    public void onNext(List<AvgScore> avgScores) {
                        Log.d(TAG, "onNext avgScore");
                        avgScoreList = avgScores;
                        for (AvgScore avgScore : avgScores) {
                            Log.d(TAG, avgScore.getSchoolName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError avgScore");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete avgScore");
                    }
                });
    }
}
