package com.app.githubtask;

import com.app.githubtask.model.GithubRepos;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("public/v1/users")
    Call<GithubRepos> getGithubRepos();

}
