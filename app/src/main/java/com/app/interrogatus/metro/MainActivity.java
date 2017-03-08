package com.app.interrogatus.metro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;

    List<String> stations = new ArrayList<>();
    HashMap<String, List<String>> treeStations = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillTreeCollection();
        fillAdapterCollection(true);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycler.setLayoutManager(llm);

        RecyclerAdapter adapter = new RecyclerAdapter();
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void fillAdapterCollection(boolean onLinies) {
        stations.clear();

        int count=0;
        for (Map.Entry entry:treeStations.entrySet()){
            List<String> value = (List)entry.getValue();

                if(onLinies==true){
                    stations.add(count,(String)entry.getKey());
                    count++;
                }
                for (String i:value){
                    stations.add(count,i);
                    count++;
                }
        }
    }

    //1.Заполняем древо станций
    private void fillTreeCollection() {

        List<String> a = Arrays.asList(new String[] {"a", "b", "c"});;
        treeStations.put("Сокольническая", Arrays.asList(
                new String[] {
                        "Кропоткинская",
                        "Красные Ворота",
                        "Чистые пруды"
                }));
        treeStations.put("Замоскворецкая", Arrays.asList(
                new String[] {
                        "Автозаводская",
                        "Царицыно",
                        "Домодедовская"
                }));
        treeStations.put("Арбатско-Покровская", Arrays.asList(
                new String[] {
                        "Арбатская",
                        "Партизанская",
                        "Семеновская"
                }));

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
