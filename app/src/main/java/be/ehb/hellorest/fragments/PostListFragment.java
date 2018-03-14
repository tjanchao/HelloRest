package be.ehb.hellorest.fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import be.ehb.hellorest.R;
import be.ehb.hellorest.interfaces.PostListFragmentListener;
import be.ehb.hellorest.model.ForumPost;
import be.ehb.hellorest.util.GetHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostListFragment extends Fragment implements AdapterView.OnItemClickListener {


    private GetHandler handler;

    private ArrayAdapter<ForumPost> postArrayAdapter;

    private PostListFragmentListener listener;




    public PostListFragment() {
        // Required empty public constructor
    }

    //factory methode om nieuwe postListFragment aan te maken
    public static PostListFragment newInstance(){
        return new PostListFragment();
    }

    @Override //onAttach: het moment waarin onze fragment in onze activity wordt gezet; na onCreate
    public void onAttach(Context context) { //context is de activity waar de fragment in zit

        listener = (PostListFragmentListener) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_list,container,false);

        ListView lvPosts = rootView.findViewById(R.id.lv_posts);
        postArrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        lvPosts.setAdapter(postArrayAdapter);

        lvPosts.setOnItemClickListener(this); //we kunnen hier "this" aanvullen omdat we al bovenaan de OnItemClickListener hebben geimplementeerd, dus we hoeven hier geen inner class aan te maken.

        //handler zorgt voor de opvulling van de lijst
        handler = new GetHandler(postArrayAdapter);


        downloadData();



        return rootView;
    }

    private void downloadData() {

        //ik wil op achtergrond data binnenhalen uitvoeren
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try { //deze try verschoven van boven Response naar hier.

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url("http://jsonplaceholder.typicode.com/posts").get().build();


                    Response response = client.newCall(request).execute();

                    Message message = new Message();
                    Bundle data = new Bundle();
                    data.putString("json_data",response.body().string());
                    message.setData(data);

                    handler.sendMessage(message);


                    //check om te zien dat json data is binnengetrokken (code 200 betekent dat het gelukt is)
                    Log.d("data",response.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        backgroundThread.start();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //int i --> de positie/rij van aangeklikte item

        ForumPost clickedPost = postArrayAdapter.getItem(i);

        listener.onPostClicked(clickedPost);

        Toast.makeText(getActivity(),"rij " + i + ": " + clickedPost,Toast.LENGTH_LONG).show();

    }
}
