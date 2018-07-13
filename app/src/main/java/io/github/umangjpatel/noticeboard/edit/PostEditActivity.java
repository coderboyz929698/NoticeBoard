package io.github.umangjpatel.noticeboard.edit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import io.github.umangjpatel.noticeboard.R;
import io.github.umangjpatel.noticeboard.databinding.ActivityPostEditBinding;
import io.github.umangjpatel.noticeboard.models.Post;

public class PostEditActivity extends AppCompatActivity {

    private ActivityPostEditBinding mEditBinding;
    private PostEditViewModel mEditViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_edit);
        mEditBinding.setLifecycleOwner(this);
        mEditViewModel = ViewModelProviders.of(this).get(PostEditViewModel.class);
        Post post = (Post) getIntent().getSerializableExtra(PostEditActivity.class.getSimpleName());
        if (post != null) {
            setTitle(R.string.edit_post_activity_label);
            mEditBinding.postNameEditText.setText(post.getFacultyName());
            mEditBinding.postSubjectEditText.setText(post.getSubject());
            mEditBinding.postBodyEditText.setText(post.getBody());
        } else
            setTitle(R.string.create_post_activity_label);
    }

    public void postNotice(View view) {
        mEditViewModel.postNotice(mEditBinding.postNameEditText.getText().toString(),
                mEditBinding.postSubjectEditText.getText().toString(),
                mEditBinding.postBodyEditText.getText().toString());
        finish();
    }


    @NonNull
    public static Intent newIntent(Context packageContext, Post post) {
        Intent intent = new Intent(packageContext, PostEditActivity.class);
        intent.putExtra(PostEditActivity.class.getSimpleName(), post);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else
            return false;
    }
}
