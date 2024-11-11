package com.example.json;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private List<Comment> comments;

    // Конструктор для инициализации списка комментариев
    public CommentAdapter(Context context, List<Comment> comments) {
        super(context, 0, comments);
        this.context = context;
        this.comments = comments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        }

        Comment comment = comments.get(position);

        TextView nameTextView = convertView.findViewById(R.id.text_view_name);
        TextView emailTextView = convertView.findViewById(R.id.text_view_email);
        TextView bodyTextView = convertView.findViewById(R.id.text_view_body);

        nameTextView.setText(comment.getName()); // Имя автора комментария
        emailTextView.setText(comment.getEmail()); // Email автора комментария
        bodyTextView.setText(comment.getBody()); // Текст комментария

        return convertView;
    }
}
