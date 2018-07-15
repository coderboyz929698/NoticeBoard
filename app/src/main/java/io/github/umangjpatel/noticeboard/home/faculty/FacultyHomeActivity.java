package io.github.umangjpatel.noticeboard.home.faculty;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import io.github.umangjpatel.noticeboard.R;
import io.github.umangjpatel.noticeboard.create.CreatePostActivity;
import io.github.umangjpatel.noticeboard.databinding.ActivityFacultyHomeBinding;
import io.github.umangjpatel.noticeboard.models.Post;
import io.github.umangjpatel.noticeboard.utils.adapters.PostsAdapter;

public class FacultyHomeActivity extends AppCompatActivity {

    private ActivityFacultyHomeBinding mHomeBinding;
    private FacultyHomeViewModel mHomeViewModel;
    private PostsAdapter mPostsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_faculty_home);
        mHomeBinding.setLifecycleOwner(this);
        mHomeViewModel = ViewModelProviders.of(this).get(FacultyHomeViewModel.class);
        mHomeBinding.postsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHomeViewModel.getDataSnapshotLiveData().observe(this, posts -> {
            if (posts != null)
                setupAdapter(posts);
        });
    }

    private void setupAdapter(List<Post> posts) {
        if (mPostsAdapter == null) {
            mPostsAdapter = new PostsAdapter(posts);
            mHomeBinding.postsRecyclerView.setAdapter(mPostsAdapter);
        } else {
            mPostsAdapter.setPosts(posts);
            mPostsAdapter.notifyDataSetChanged();
        }
    }

    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, FacultyHomeActivity.class);
    }


    public void createNotice(View view) {
        startActivity(CreatePostActivity.newIntent(this));
    }
}
