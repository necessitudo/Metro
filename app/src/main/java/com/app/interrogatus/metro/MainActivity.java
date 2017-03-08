package com.app.interrogatus.metro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
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
    RecyclerAdapter adapter = new RecyclerAdapter();
    boolean sortOnLinies = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillTreeCollection();
        fillAdapterCollection();

        recycler = (RecyclerView) findViewById(R.id.recycler);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycler.setLayoutManager(llm);

        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private void fillAdapterCollection() {
        stations.clear();

        int count=0;
        for (Map.Entry entry:treeStations.entrySet()){
            List<String> value = (List)entry.getValue();

                if(sortOnLinies==true){
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

           View view = null;

           switch (viewType){
               case 1:
                    view = inflater.inflate(R.layout.listitemgroup, parent, false);
                    break;
               case 0:
                    view = inflater.inflate(R.layout.listitem, parent, false);
                    break;
           }
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

        @Override
        public int getItemViewType(int position) {
            if (treeStations.containsKey(stations.get(position))==true)
            {
                return  1;
            }
            else return 0;

        }
    }
    class StationHolder extends RecyclerView.ViewHolder{

        TextView namestation;

        public StationHolder(View itemView) {
            super(itemView);

            namestation = (TextView) itemView.findViewById(R.id.namestation);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.sort){
            sortOnLinies=!sortOnLinies;

            fillAdapterCollection();

            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
