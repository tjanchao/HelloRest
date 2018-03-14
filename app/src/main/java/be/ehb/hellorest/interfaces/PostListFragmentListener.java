package be.ehb.hellorest.interfaces;

import be.ehb.hellorest.model.ForumPost;

/**
 * Created by Q on 14-3-2018.
 */

public interface PostListFragmentListener {

    //public kan je ook weglaten bij interfaces, er wordt bij interfaces automatisch geinfereerd dat methodes in interfaces public zijn
    public void onPostClicked(ForumPost forumPost);

}
