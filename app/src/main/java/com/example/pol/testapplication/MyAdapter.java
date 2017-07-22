package com.example.pol.testapplication;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private SortedList<Oompa> mSortedList;

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;

        ViewHolder(View itemView){
            super(itemView);

            //Initiate your code here...

        }

        void setOompa(Oompa model) {
            //Update your UI with the data model passed here...
            name.setText(model.getName());
        }
    }

    public MyAdapter() {
        mSortedList = new SortedList<>(Oompa.class, new SortedListAdapterCallback<Oompa>(this) {
            @Override
            public int compare(Oompa o1, Oompa o2) {
                //This gets called to find the ordering between objects in the array.
                if (o1.getId() < o2.getId()) {
                    return -1;
                } else if (o1.getId() > o2.getId()) {
                    return 1;
                } else {
                    return 0;
                }
            }

            @Override
            public boolean areContentsTheSame(Oompa oldItem, Oompa newItem) {
                //This is to see of the content of this object has changed. These items are only considered equal if areItemsTheSame() returned true.

                //If this returns false, onBindViewHolder() is called with the holder containing the item, and the item's position.
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areItemsTheSame(Oompa item1, Oompa item2) {
                //Checks to see if these two items are the same. If not, it is added to the list, otherwise, check if content has changed.
                return item1.equals(item2);
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_oompas_list, parent, false);
        TextView tv = (TextView) itemView.findViewById(R.id.text);
        Log.d("reach", "oncReateViewholder");
        tv.setText("TEEEXT");
        //findViewById...

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Just update the holder with the object in the sorted list from the given position
        Oompa model = mSortedList.get(position);
        Log.d("reach", "true");
        if (model != null) {
            Log.d("reach", "reached");
            holder.setOompa(model);
        }
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public void resetList(List<Oompa> models) {
        //If you are performing multiple changes, use the batching methods to ensure proper animation.
        mSortedList.beginBatchedUpdates();
        mSortedList.clear();
        mSortedList.addAll(models);
        mSortedList.endBatchedUpdates();
    }

    //The following methods each modify the data set and automatically handles calling the appropriate 'notify' method on the adapter.
    public void addModel(Oompa model) {
        mSortedList.add(model);
    }

    public void addModels(List<Oompa> models) {
        mSortedList.addAll(models);
    }

    public void clear() {
        mSortedList.clear();
    }

    public void removeModel(Oompa model) {
        mSortedList.remove(model);
    }

    public void removeModelAt(int i) {
        mSortedList.removeItemAt(i);
    }
}