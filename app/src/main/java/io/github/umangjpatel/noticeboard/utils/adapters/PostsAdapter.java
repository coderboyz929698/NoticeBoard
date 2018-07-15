package io.github.umangjpatel.noticeboard.utils.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.umangjpatel.noticeboard.databinding.PostListItemBinding;
import io.github.umangjpatel.noticeboard.models.Post;
import io.github.umangjpatel.noticeboard.utils.viewholders.PostViewHolder;

public class PostsAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Post> mPosts;

    public PostsAdapter(List<Post> posts) {
        mPosts = posts;
    }

    public void setPosts(List<Post> posts) {
        mPosts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PostListItemBinding itemBinding = PostListItemBinding.inflate(inflater, parent, false);
        return new PostViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = mPosts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
