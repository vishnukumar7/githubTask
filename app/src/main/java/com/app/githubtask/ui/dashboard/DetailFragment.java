package com.app.githubtask.ui.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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

    public static DetailFragment getInstance(Bundle bundle){
        instance=new DetailFragment();
        instance.setArguments(bundle);
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
            binding.comments.setEnabled(true);
            binding.userMail.setText(dataItem.getEmail());
            binding.userId.setText(String.valueOf(dataItem.getId()));
            binding.gender.setText(dataItem.getGender());
            binding.status.setText(dataItem.getStatus());
            binding.submitComments.setVisibility(View.VISIBLE);
            binding.comments.setText(dataItem.getComments());
            binding.submitComments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(binding.comments.getText())) {
                        binding.comments.setError(getString(R.string.comments_required));
                    } else if(dataItem.getComments().equals(binding.comments.getText().toString())){
                        Toast.makeText(getActivity(), getString(R.string.same_as_previous_cooments), Toast.LENGTH_SHORT).show();
                    }else {
                        dataItem.setComments(binding.comments.getText().toString());
                        userDao.update(dataItem);
                        binding.comments.setText(binding.comments.getText().toString());
                        hideSoftKeyboard(getActivity());
                        Toast.makeText(getActivity(), getString(R.string.save_successfully), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    public static void hideSoftKeyboard(Activity context) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(context
                    .getCurrentFocus().getWindowToken(), 0);
        } catch (Exception npe) {
            npe.printStackTrace();
        }
    }
}