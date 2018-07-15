package io.github.umangjpatel.noticeboard.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.github.umangjpatel.noticeboard.R;
import io.github.umangjpatel.noticeboard.create.CreatePostActivity;
import io.github.umangjpatel.noticeboard.databinding.ActivityNoticeDetailBinding;
import io.github.umangjpatel.noticeboard.models.Post;

public class NoticeDetailActivity extends AppCompatActivity {

    private static final String DETAIL_KEY_POST = "detail_post";
    private ActivityNoticeDetailBinding mDetailBinding;
    private Post mPost;

    public static Intent newIntent(Context packageContext, Post post) {
        Intent intent = new Intent(packageContext, NoticeDetailActivity.class);
        intent.putExtra(CreatePostActivity.class.getSimpleName(), post);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_notice_detail);
        mDetailBinding.setLifecycleOwner(this);
        mPost = (Post) getIntent().getSerializableExtra(CreatePostActivity.class.getSimpleName());
        if (savedInstanceState != null)
            mPost = (Post) savedInstanceState.getSerializable(DETAIL_KEY_POST);
        if (mPost != null) {
            mDetailBinding.postNameTextView.setText(mPost.getFacultyName());
            mDetailBinding.postSubjectTextView.setText(mPost.getSubject());
            mDetailBinding.postBodyTextView.setText(mPost.getBody());
            setTitle(mPost.getSubject());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DETAIL_KEY_POST, mPost);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}
