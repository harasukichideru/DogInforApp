package com.example.dogapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.example.dogapp.DogsApiService;
import androidx.navigation.NavDirections;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private DogsApiService apiService;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = new DogsApiService();
        apiService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        ListFragment listFragment = new ListFragment();

                        fragmentManager.beginTransaction().add(R.id.frame, listFragment).addToBackStack(null).commit();

                        ArrayList<DogBreed> al_list = new ArrayList<>(dogBreeds.size());
                        al_list.addAll(dogBreeds);

                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("list", al_list);


                        listFragment.setArguments(bundle);

                        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                            boolean done = getSupportFragmentManager().popBackStackImmediate();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
}
