package com.cavanosa.crudretrofitapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cavanosa.crudretrofitapp.R;
import com.cavanosa.crudretrofitapp.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>
{
    private Context context;
    private List<Person> lists;
    private ItemClickListener itemClickListener;

    public PersonAdapter(Context context, List<Person> lists, ItemClickListener itemClickListener)
    {
        this.context = context;
        this.lists = lists;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.PersonViewHolder holder, int position)
    {
        Person person = lists.get(position);

        holder.name_text.setText(person.getName());
        holder.hobby_text.setText(person.getHobby());
    }

    @Override
    public int getItemCount()
    {
        return lists.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public LinearLayout linearLayout;
        public TextView name_text, hobby_text;
        ItemClickListener itemClickListener;

        public PersonViewHolder(@NonNull View view, ItemClickListener itemClickListener)
        {
            super(view);
            linearLayout = view.findViewById(R.id.linear_layout);
            name_text = view.findViewById(R.id.name_text);
            hobby_text = view.findViewById(R.id.hobby_text);

            this.itemClickListener = itemClickListener;
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener
    {
        void onItemClick(View view, int position);
    }

}
