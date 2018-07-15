package io.github.umangjpatel.noticeboard.create;

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
import io.github.umangjpatel.noticeboard.databinding.ActivityCreatePostBinding;

public class CreatePostActivity extends AppCompatActivity {

    private ActivityCreatePostBinding mEditBinding;
    private CreatePostViewModel mEditViewModel;

    @NonNull
    public static Intent newIntent(Context packageContext) {
        return new Intent(packageContext, CreatePostActivity.class);
    }

    public void postNotice(View view) {
        mEditViewModel.postNotice(mEditBinding.postNameEditText.getText().toString(),
                mEditBinding.postSubjectEditText.getText().toString(),
                mEditBinding.postBodyEditText.getText().toString());
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_post);
        mEditBinding.setLifecycleOwner(this);
        mEditViewModel = ViewModelProviders.of(this).get(CreatePostViewModel.class);

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
