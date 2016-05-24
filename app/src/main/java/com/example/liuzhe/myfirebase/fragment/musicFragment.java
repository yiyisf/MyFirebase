package com.example.liuzhe.myfirebase.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.liuzhe.myfirebase.MusicViewHolder;
import com.example.liuzhe.myfirebase.MyApp;
import com.example.liuzhe.myfirebase.R;
import com.example.liuzhe.myfirebase.Song;
import com.firebase.client.Firebase;
import com.firebase.client.core.operation.ListenComplete;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class MusicFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private Firebase mRef;
    private View view;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MusicFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MusicFragment newInstance(int columnCount) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_list, container, false);

        // Set the adapter
        if (view instanceof RelativeLayout) {
            Context context = view.findViewById(R.id.list).getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
            mRef = MyApp.getFirebase().child("music");
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Song, MusicViewHolder>(Song.class,
                    R.layout.fragment_song,
                    MusicViewHolder.class,
                    mRef) {
                @Override
                protected void populateViewHolder(final MusicViewHolder musicViewHolder, final Song song, int i) {
                    try {
                        musicViewHolder.song_name.setText(song.getName());
                        musicViewHolder.song_item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "点击了" + song.getName(), Toast.LENGTH_SHORT).show();
                                Log.e("test:", "before");
                                LinearLayoutCompat player = (LinearLayoutCompat) view.findViewById(R.id.player);
                                player.setVisibility(LinearLayoutCompat.VISIBLE);
                                Log.e("test:", "after");
                            }


                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            recyclerView.setAdapter(firebaseRecyclerAdapter);

        }
        return view;
    }

    private void showplayer(boolean show) {
        try {
            System.out.print("turn start..............");
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.findViewById(R.id.list).getLayoutParams();
//            params.height = 200;
//            view.findViewById(R.id.list).setLayoutParams(params);
            LinearLayoutCompat player = (LinearLayoutCompat) view.findViewById(R.id.player);
            player.setVisibility(show ? player.GONE : player.VISIBLE);
            System.out.print("turn end.................");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
