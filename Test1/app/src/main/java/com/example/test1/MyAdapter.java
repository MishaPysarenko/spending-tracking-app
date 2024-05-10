package com.example.test1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<DataItem> dataList;
    private Context context;


    public MyAdapter(List<DataItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataItem item = dataList.get(position);
        holder.bindData(item);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Логика для удаления элемента списка
                if (position != RecyclerView.NO_POSITION) {
                    // Удаление элемента из списка данных
                    dataList.remove(position);
                    // Уведомление адаптера об удалении элемента
                    notifyItemRemoved(position);
                    // Уведомление менеджера о удалении элемента из памяти
                    MemoryMeneger.delete(item.getName());
                    // Всплывающее уведомление о нажатии на кнопку
                    Toast.makeText(context, "Об'єкт:\"" + item.getName() + "\" був видаланим" + item.getDate(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView sumTextView;
        private TextView nameTextView;
        private TextView date;
        private TextView isStaible;
        private Button deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sumTextView = itemView.findViewById(R.id.Sum);
            nameTextView = itemView.findViewById(R.id.Name);
            date = itemView.findViewById(R.id.date);
            isStaible = itemView.findViewById(R.id.IsStaible);
            deleteButton = itemView.findViewById(R.id.Del);
        }

        public void bindData(DataItem item) {
            sumTextView.setText(item.getSum());
            nameTextView.setText(item.getName());
            date.setText(item.getDate());
            if (item.getIsStable())
            {
                isStaible.setVisibility(View.VISIBLE);
            }
        }
    }
}
