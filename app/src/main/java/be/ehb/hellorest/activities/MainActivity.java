package be.ehb.hellorest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.ehb.hellorest.R;
import be.ehb.hellorest.fragments.PostDetailFragment;
import be.ehb.hellorest.fragments.PostListFragment;
import be.ehb.hellorest.interfaces.PostListFragmentListener;
import be.ehb.hellorest.model.ForumPost;

public class MainActivity extends AppCompatActivity implements PostListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, PostListFragment.newInstance()).commit();
    }

    @Override
    public void onPostClicked(ForumPost forumPost) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, PostDetailFragment.newInstance(forumPost)).addToBackStack("back").commit();

    }
}
