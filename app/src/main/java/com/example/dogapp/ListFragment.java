package com.example.dogapp;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private RecyclerView rvDogs;
    private MyAdapter adapter;
    private ArrayList<DogBreed> newList;

    public ListFragment() {
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        onDestroyOptionsMenu();
        if (getArguments() != null) {
            newList = getArguments().getParcelableArrayList("list");
            rvDogs = (RecyclerView) view.findViewById(R.id.rv_dogs);
            setHasOptionsMenu(true);

            adapter = new MyAdapter(getContext(), newList);
            rvDogs.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
            rvDogs.setHasFixedSize(true);

            rvDogs.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                private final ColorDrawable background = new ColorDrawable(getResources().getColor(R.color.background));

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    adapter.showMenu(viewHolder.getAdapterPosition());
                }

                @Override
                public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                    View itemView = viewHolder.itemView;

                    if (dX > 0) {
                        background.setBounds(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + ((int) dX), itemView.getBottom());
                    } else if (dX < 0) {
                        background.setBounds(itemView.getRight() + ((int) dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    } else {
                        background.setBounds(0, 0, 0, 0);
                    }

                    background.draw(c);
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
            itemTouchHelper.attachToRecyclerView(rvDogs);

            rvDogs.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    adapter.closeMenu();
                }
            });

            adapter.setOnClickListener(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, DogBreed dog) {
                    Bundle bundle = new Bundle();
                    FragmentManager fragmentManager = getParentFragmentManager();
                    detailsFragment fragment = new detailsFragment();
                    fragmentManager.beginTransaction().replace(R.id.frame, fragment).addToBackStack(null).commit();

                    bundle.putParcelable("dog", dog);

                    fragment.setArguments(bundle);
                }
            });

        }
        return view;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        MenuItem mSearch = menu.findItem(R.id.appSearchBar);
        SearchView mSearchView = (SearchView) mSearch.getActionView();

        mSearchView.setQueryHint("Search dog by name");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.isEmpty()) {
                    ArrayList<DogBreed> filteredList = new ArrayList<>();
                    for (DogBreed dog : newList) {
                        if (dog.getName().toLowerCase().contains(s.toLowerCase())) {
                            filteredList.add(dog);
                        }
                    }
                    adapter = new MyAdapter(getContext(), filteredList);
                    rvDogs.setAdapter(adapter);
                } else {
                    adapter = new MyAdapter(getContext(), newList);
                    rvDogs.setAdapter(adapter);
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }
}