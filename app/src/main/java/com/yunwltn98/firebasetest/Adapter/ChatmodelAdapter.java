package com.yunwltn98.firebasetest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.yunwltn98.firebasetest.R;
import com.yunwltn98.firebasetest.model.Chatmodel;

import java.util.List;

public class ChatmodelAdapter extends RecyclerView.Adapter<ChatmodelAdapter.ViewHolder>{

    Context context;
    List<Chatmodel> chatmodelList;
    String Uid;

    public ChatmodelAdapter(Context context, List<Chatmodel> chatmodelList) {
        this.context = context;
        this.chatmodelList = chatmodelList;
        Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public ChatmodelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ChatmodelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatmodelAdapter.ViewHolder holder, int position) {
        final String uid = chatmodelList.get(position).getUid();
        Chatmodel chatmodel = chatmodelList.get(position);
        holder.txtName.setText(chatmodel.getName());
        holder.txtChat.setText(chatmodel.getMsg());

    }

    @Override
    public int getItemCount() {
        return chatmodelList.size();
    }

    // 4. 생성자를 만들고 생성자에서 뷰를 연결시키는 코드를 작성하고 클릭 이벤트도 작성한다
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtChat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtChat = itemView.findViewById(R.id.txtChat);

        }
    }
}
