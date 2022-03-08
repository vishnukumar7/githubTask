package com.app.githubtask.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.app.githubtask.R;
import com.app.githubtask.databinding.FragmentDetailBinding;
import com.app.githubtask.db.DatabaseClient;
import com.app.githubtask.model.DataItem;
import com.app.githubtask.model.UserDao;
import com.app.githubtask.ui.home.HomeFragment;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private static int lastId=-1;

    private static DetailFragment instance=null;

    public static DetailFragment getInstance(){
        if(instance==null)
            instance=new DetailFragment();
        return instance;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!= null) {
            Bundle bundle = getArguments();
            lastId=bundle.getInt("id");
        }

        if (lastId!=-1){
            DatabaseClient databaseClient = new DatabaseClient(getActivity());
            UserDao userDao = databaseClient.getAppDatabase().userDao();
            DataItem dataItem = userDao.getItemById(lastId);
            binding.userName.setText(dataItem.getName());
            binding.userMail.setText(dataItem.getEmail());
            binding.userId.setText(String.valueOf(dataItem.getId()));
            binding.gender.setText(dataItem.getGender());
            binding.status.setText(dataItem.getStatus());
            binding.comments.setText(dataItem.getComments());
        }
    }
}