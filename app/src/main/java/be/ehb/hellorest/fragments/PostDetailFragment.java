package be.ehb.hellorest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.ehb.hellorest.R;
import be.ehb.hellorest.model.ForumPost;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostDetailFragment extends Fragment {

    private ForumPost forumPost;

    //factory-methode: ik maak hier een nieuwe fragment aan voor 1 specifieke forumpost.
    public static PostDetailFragment newInstance(ForumPost fp){

        PostDetailFragment instance = new PostDetailFragment();
        instance.forumPost = fp;

        return instance;


    }

    public PostDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_detail, container,false);

        TextView tvTitle = rootView.findViewById(R.id.tv_detail_title);
        TextView tvBody = rootView.findViewById(R.id.tv_detail_body);

        tvTitle.setText(forumPost.getTitle());
        tvBody.setText(forumPost.getBody());

        return rootView;
    }

}
