package com.app.githubtask.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.app.githubtask.ApiClient;
import com.app.githubtask.R;
import com.app.githubtask.databinding.FragmentHomeBinding;
import com.app.githubtask.databinding.UserListItemBinding;
import com.app.githubtask.model.DataItem;
import com.app.githubtask.model.GithubRepos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    ArrayList<DataItem> itemArrayList = new ArrayList<>();
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }


    private void getData() {
        Call<GithubRepos> reposCall = ApiClient.getAPI().getGithubRepos();
        reposCall.enqueue(new Callback<GithubRepos>() {
            @Override
            public void onResponse(@NonNull Call<GithubRepos> call, @NonNull Response<GithubRepos> response) {
                if (response.isSuccessful() && response.body() != null) {

                }
            }

            @Override
            public void onFailure(@NonNull Call<GithubRepos> call, @NonNull Throwable t) {

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