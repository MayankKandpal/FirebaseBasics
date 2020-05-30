package com.example.firstfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText etvar1;
    EditText etvar;
    ListView lvr;
    TextView tvtest;
    //ArrayList<custom> customs;
    //ArrayList<String> notes;
   // ArrayList<String> notes2;
    custom p1 = new custom();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        btn = findViewById(R.id.btn);
        etvar = findViewById(R.id.etvar);
        etvar1 = findViewById(R.id.etvar1);
        lvr = findViewById(R.id.lvr);
        tvtest = findViewById(R.id.tvtest);

    //    notes = new ArrayList<>();
      //  notes2 = new ArrayList<>();
       // final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                //this,
               // R.layout.item_file,
               // R.id.tvListView,
              //  notes);
     final CustomAdapter customAdapter = new CustomAdapter();
      lvr.setAdapter(customAdapter);

      final DatabaseReference dbRef =FirebaseDatabase.getInstance().getReference();
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String note = etvar.getText().toString();
                String note1 = etvar1.getText().toString();
                dbRef.child("node").push().setValue(note);
               dbRef.child("todo").push().setValue(note1);
            }
        });
        dbRef.child("node").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              String data = dataSnapshot.getValue().toString();
              p1.setName(data);
              customAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

          @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

           @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
           }
        });
        }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public custom getItem(int position) {

            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item_view = getLayoutInflater().inflate(
                    R.layout.item_file,
                    parent,
                    false
            );
            TextView tvListView  = item_view.findViewById(R.id.tvListView);
            TextView tvListView1 = item_view.findViewById(R.id.tvListView1);
            tvListView.setText(p1.getName());


            return item_view;
        }
    }

}