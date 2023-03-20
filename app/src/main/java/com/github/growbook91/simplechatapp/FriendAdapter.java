package com.github.growbook91.simplechatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private Friend[] friends;

    public FriendAdapter(Friend[] friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent,false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        holder.bind(friends[position]);
    }

    @Override
    public int getItemCount() {
        return friends.length;
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;
        TextView profileName;
        TextView profileStatus;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            profileName = itemView.findViewById(R.id.text_view_name);
            profileStatus = itemView.findViewById(R.id.text_view_status);
            profileImage = itemView.findViewById(R.id.image_view_profile_image);
        }

        public void bind(Friend profile){
            profileName.setText(profile.name);
            profileStatus.setText(profile.status);
            profileImage.setImageResource(profile.image);
        }
    }
}
