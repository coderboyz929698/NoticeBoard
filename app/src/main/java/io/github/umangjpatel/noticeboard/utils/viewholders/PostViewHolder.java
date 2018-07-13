package io.github.umangjpatel.noticeboard.utils.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.github.umangjpatel.noticeboard.databinding.PostListItemBinding;
import io.github.umangjpatel.noticeboard.edit.PostEditActivity;
import io.github.umangjpatel.noticeboard.models.Post;

public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private PostListItemBinding mItemBinding;
    private Post mPost;

    public PostViewHolder(PostListItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    public void bind(Post post, boolean isFaculty) {
        mPost = post;
        mItemBinding.postFacultyTextView.setText(post.getFacultyName());
        mItemBinding.postSubjectTextView.setText(post.getSubject());
        mItemBinding.postDateTextView.setText(formatDate(post.getDate()));
        itemView.setOnClickListener(isFaculty ? this : null);
        mItemBinding.executePendingBindings();
    }

    private String formatDate(long date) {
        Date postDate = new Date(date);
        SimpleDateFormat formatter
                = new SimpleDateFormat("E, MMM dd, yyyy", Locale.ENGLISH);
        return formatter.format(postDate);
    }

    @Override
    public void onClick(View v) {
        mItemBinding
                .getRoot()
                .getContext()
                .startActivity(PostEditActivity.newIntent(mItemBinding.getRoot().getContext(),
                        mPost));
    }
}
