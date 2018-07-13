package io.github.umangjpatel.noticeboard.home.faculty;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.github.umangjpatel.noticeboard.models.Post;
import io.github.umangjpatel.noticeboard.utils.database.FirebaseQueryLiveData;

public class FacultyHomeViewModel extends AndroidViewModel {

    private static final DatabaseReference POSTS_REF =
            FirebaseDatabase.getInstance().getReference();

    private final MediatorLiveData<List<Post>> mPostsLiveData;

    private final FirebaseQueryLiveData mFirebaseQueryLiveData;

    public FacultyHomeViewModel(@NonNull Application application) {
        super(application);
        mFirebaseQueryLiveData = new FirebaseQueryLiveData(POSTS_REF);
        mPostsLiveData = new MediatorLiveData<>();
        mPostsLiveData.addSource(mFirebaseQueryLiveData, dataSnapshot -> {
            if (dataSnapshot != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Post> postList = new ArrayList<>();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                            postList.add(postSnapshot.getValue(Post.class));
                        mPostsLiveData.postValue(postList);
                    }
                }).start();
            } else
                mPostsLiveData.setValue(null);

        });
    }

    @NonNull
    public LiveData<List<Post>> getDataSnapshotLiveData() {
        return mPostsLiveData;
    }
}
