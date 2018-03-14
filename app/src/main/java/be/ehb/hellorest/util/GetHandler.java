package be.ehb.hellorest.util;

import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.ehb.hellorest.model.ForumPost;

/**
 * Created by Q on 14-3-2018.
 */

public class GetHandler extends Handler {

    //in de handler worden messages opgevangen vanuit een thread en wordt er iets mee gedaan/verwerkt
    //maak na het maken van deze handler een instantie van deze handler aan in PostListFragment

    ArrayAdapter<ForumPost> forumPostArrayAdapter;

    //constructor
    public GetHandler(ArrayAdapter<ForumPost> forumPostArrayAdapter) {
        this.forumPostArrayAdapter = forumPostArrayAdapter;
    }

    @Override
    public void handleMessage(Message message) {

        try {

            String postData = message.getData().getString("json_data");

            JSONArray jsonArray = new JSONArray(postData);

            int arrayLengte = jsonArray.length();



            for (int i = 0 ; i < arrayLengte; i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                ForumPost forumPost = new ForumPost(
                        jsonObject.getInt("id"),
                        jsonObject.getInt("userId"),
                        jsonObject.getString("title"),
                        jsonObject.getString("body"));

                forumPostArrayAdapter.add(forumPost);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
