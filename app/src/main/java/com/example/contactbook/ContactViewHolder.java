package com.example.contactbook;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private  TextView phone;
    private TextView email;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name_contact);
        phone = itemView.findViewById(R.id.phone_contact);
        email = itemView.findViewById(R.id.email_contact);
    }
}
