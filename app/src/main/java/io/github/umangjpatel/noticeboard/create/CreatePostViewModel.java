package io.github.umangjpatel.noticeboard.create;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.github.umangjpatel.noticeboard.repositories.PostRepository;

public class CreatePostViewModel extends AndroidViewModel {

    private PostRepository mPostRepository;

    public CreatePostViewModel(@NonNull Application application) {
        super(application);
        mPostRepository = PostRepository.getInstance();
    }

    public void postNotice(String name, String subject, String body) {
        mPostRepository.publishPost(name, subject, body);
    }
}
