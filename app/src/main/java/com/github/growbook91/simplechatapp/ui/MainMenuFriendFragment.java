package com.github.growbook91.simplechatapp.ui;

import static com.github.growbook91.simplechatapp.data.UserModel.name;
import static com.github.growbook91.simplechatapp.data.UserModel.status;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.growbook91.simplechatapp.Friend;
import com.github.growbook91.simplechatapp.FriendAdapter;
import com.github.growbook91.simplechatapp.R;

public class MainMenuFriendFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main_menu_friend, container, false);
        initFriendList(rootView);

        getUserProfile(rootView);

        return rootView;
    }

    private void getUserProfile(ViewGroup rootView) {
        TextView userName = rootView.findViewById(R.id.text_view_name);
        TextView userStatus = rootView.findViewById(R.id.text_view_status);


        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                userName.setText(newName);
            }
        };

        final Observer<String> statusObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                userStatus.setText(newName);
            }
        };

        name.observe(getViewLifecycleOwner(), nameObserver);
        status.observe(getViewLifecycleOwner(), statusObserver);
    }


    private void initFriendList(ViewGroup rootView) {
        RecyclerView friendList = rootView.findViewById(R.id.recycler_view_friends);




        Friend[] friends = {
                new Friend("이지혜", "Hi", R.drawable.ic_launcher_foreground),
                new Friend("주성민", "Hi", R.drawable.ic_launcher_foreground),
                new Friend("남성호", "Hi", R.drawable.ic_launcher_foreground),
                new Friend("천성권", "Hi", R.drawable.ic_launcher_foreground),
        };

        FriendAdapter adapter = new FriendAdapter(friends);
        friendList.setAdapter(adapter);
    }
}