package com.example.liuzhe.myfirebase.fragment;

/**
 * Created by liuzhe on 2016/5/11.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListViewCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuzhe.myfirebase.MessageList;
import com.example.liuzhe.myfirebase.R;
import com.example.liuzhe.myfirebase.UserInfo;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class UsersFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    FirebaseListAdapter firebaseListAdapter;
    static Firebase mRef;
    private View rootView;
    public UsersFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static UsersFragment newUser(Firebase firebase) {
        mRef = firebase.child("alluser");
        UsersFragment fragment = new UsersFragment();
        return fragment;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_users, container, false);

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        ListViewCompat userList = (ListViewCompat) rootView.findViewById(R.id.userView);

        firebaseListAdapter = new FirebaseListAdapter<UserInfo>(getActivity(), UserInfo.class, R.layout.simple_list_item, mRef) {
            @Override
            protected void populateView(View view, UserInfo userInfo, int i) {
                ((TextView) view.findViewById(R.id.spinner_name)).setText(userInfo.getName());
                ((TextView) view.findViewById(R.id.spinner_email)).setText(userInfo.getEmail());

                String photo = userInfo.getPhoto();
                if (photo != null) {
                    byte[] imageAsBytes = Base64.decode(photo.getBytes(), Base64.DEFAULT);
                    ((ImageView) view.findViewById(R.id.spinner_photo)).setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
                }
            }
        };

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserInfo touser = (UserInfo) firebaseListAdapter.getItem(position);
                Intent talkIntent = new Intent(getActivity(), MessageList.class);
                talkIntent.putExtra("touser", touser.getName());
                startActivity(talkIntent);
            }
        });

        userList.setAdapter(firebaseListAdapter);
    }
}
