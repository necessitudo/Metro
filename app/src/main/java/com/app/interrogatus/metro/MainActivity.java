package com.app.interrogatus.metro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;

    List<String> stations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stations.add(0, "Полянка");
        stations.add(1, "Автозаводская");
        stations.add(2, "Боровицкая");

        recycler = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycler.setLayoutManager(llm);

        RecyclerAdapter adapter = new RecyclerAdapter();
        recycler.setAdapter(adapter);


    }

   class  RecyclerAdapter extends RecyclerView.Adapter<StationHolder>{

       @Override
       public StationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           LayoutInflater inflater = LayoutInflater.from(parent.getContext());
           View view = inflater.inflate(R.layout.listitem, parent, false);
           StationHolder holder = new StationHolder(view);
           return holder;
       }

       @Override
       public void onBindViewHolder(StationHolder holder, int position) {
           String station = stations.get(position);

               holder.namestation.setText(station);

       }

       @Override
       public int getItemCount() {
           return stations.size();
       }

   }
    class StationHolder extends RecyclerView.ViewHolder{

        TextView namestation;

        public StationHolder(View itemView) {
            super(itemView);

            namestation = (TextView) itemView.findViewById(R.id.namestation);

        }
    }
}
