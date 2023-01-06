package com.example.contactbook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.idem_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contactName.setText(DB.contactList.get(position).getName());
        holder.contactPhone.setText(DB.contactList.get(position).getPhone());
        holder.contactEmail.setText(DB.contactList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return DB.contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private TextView contactName, contactPhone, contactEmail;
        private ImageView favorite;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.name_contact);
            contactPhone = itemView.findViewById(R.id.phone_contact);
            contactEmail = itemView.findViewById(R.id.email_contact);
            favorite = itemView.findViewById(R.id.favorite_img);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intendUpdate = new Intent(context, UpdateActivity.class);
                    intendUpdate.putExtra("userData", "" + getLayoutPosition());
                    context.startActivity(intendUpdate);
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, itemView.getId(), 0, "Call").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    String phone = "tel:" + DB.contactList.get(getLayoutPosition()).getPhone();
                    Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
                    //context.startActivity(intentCall);

                    return false;
                }
            });
        }
    }
}
