package com.app.githubtask.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.githubtask.ApiClient;
import com.app.githubtask.R;
import com.app.githubtask.databinding.FragmentHomeBinding;
import com.app.githubtask.databinding.UserListItemBinding;
import com.app.githubtask.model.DataItem;
import com.app.githubtask.model.GithubRepos;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
private String TAG="HomeFragment";
    ArrayList<DataItem> itemArrayList = new ArrayList<>();
    UserAdapter adapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(false);
        adapter = new UserAdapter(itemArrayList);
        binding.recyclerView.setAdapter(adapter);
        getData();
    }

    private void getData() {
        Call<GithubRepos> reposCall = ApiClient.getAPI().getGithubRepos();
        reposCall.enqueue(new Callback<GithubRepos>() {
            @Override
            public void onResponse(@NonNull Call<GithubRepos> call, @NonNull Response<GithubRepos> response) {
                Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                if (response.isSuccessful() && response.body() != null) {
                    itemArrayList.clear();
                    itemArrayList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GithubRepos> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }


    class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        private final ArrayList<DataItem> itemArrayList;

        UserAdapter(ArrayList<DataItem> itemArrayList) {
            this.itemArrayList = itemArrayList;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            UserListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_list_item, parent, false);
            return new UserViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            DataItem dataItem = itemArrayList.get(position);
            holder.itemBinding.userNameView.setText(dataItem.getName());
            holder.itemBinding.userMailView.setText(dataItem.getEmail());
            holder.itemBinding.userStatusView.setText(dataItem.getStatus());
            holder.itemBinding.comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return itemArrayList.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {

            UserListItemBinding itemBinding;

            public UserViewHolder(@NonNull UserListItemBinding itemView) {
                super(itemView.getRoot());
                itemBinding = itemView;
            }
        }
    }

}