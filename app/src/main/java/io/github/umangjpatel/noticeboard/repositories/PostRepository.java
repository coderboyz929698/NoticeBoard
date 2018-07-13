package io.github.umangjpatel.noticeboard.repositories;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import io.github.umangjpatel.noticeboard.models.Post;

public class PostRepository {

    private DatabaseReference mPostDatabaseReference;

    private static PostRepository sPostRepository;

    public static PostRepository getInstance() {
        if (sPostRepository == null) {
            synchronized (PostRepository.class) {
                if (sPostRepository == null) {
                    sPostRepository = new PostRepository();
                }
            }
        }
        return sPostRepository;
    }

    private PostRepository() {
        mPostDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void publishPost(String name, String subject, String body) {
        Post post = new Post();
        post.setFacultyName(name);
        post.setSubject(subject);
        post.setBody(body);
        post.setDate(new Date().getTime());
        mPostDatabaseReference
                .push()
                .setValue(post);
    }
}
