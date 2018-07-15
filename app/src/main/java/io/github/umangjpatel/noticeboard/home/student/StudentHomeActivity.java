package io.github.umangjpatel.noticeboard.home.student;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import io.github.umangjpatel.noticeboard.R;
import io.github.umangjpatel.noticeboard.databinding.ActivityStudentHomeBinding;
import io.github.umangjpatel.noticeboard.models.Post;
import io.github.umangjpatel.noticeboard.utils.adapters.PostsAdapter;

public class StudentHomeActivity extends AppCompatActivity {

    private ActivityStudentHomeBinding mHomeBinding;
    private StudentHomeViewModel mHomeViewModel;
    private PostsAdapter mPostsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_student_home);
        mHomeBinding.setLifecycleOwner(this);
        mHomeViewModel = ViewModelProviders.of(this).get(StudentHomeViewModel.class);
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
        return new Intent(packageContext, StudentHomeActivity.class);
    }
}
