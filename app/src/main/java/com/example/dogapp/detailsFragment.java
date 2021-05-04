package com.example.dogapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class detailsFragment extends Fragment {

    private ImageView imageView;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6;

    // TODO: Rename and change types of parameters
    private static String name;
    private static String bred_for;
    private static String breed_group;
    private static String temperament;
    private static String origin;
    private static String life_span;
    private static String url;

    public detailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            DogBreed dog = bundle.getParcelable("dog");

            url = dog.getUrl();
            name = dog.getName();
            if(dog.getBred_for()!=null) bred_for = "Bred for: " + dog.getBred_for();
            if(dog.getBreed_group() != null) breed_group = "Breed group: " + dog.getBreed_group();
            if(dog.getTemperament() != null) temperament = "Temperament: " + dog.getTemperament();
            if(dog.getOrigin()!=null) origin = "Origin: " + dog.getOrigin();
            if(dog.getLifeSpan()!=null) life_span = "Life span: " + dog.getLifeSpan();
            }
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        imageView = view.findViewById(R.id.image_view_1);
        tv1 = view.findViewById(R.id.name_1);
        tv2 = view.findViewById(R.id.bred_for_1);
        tv3 = view.findViewById(R.id.breed_group_1);
        tv4 = view.findViewById(R.id.temperament_1);
        tv5 = view.findViewById(R.id.origin_1);
        tv6 = view.findViewById(R.id.life_span_1);

        Picasso.get().load(url).fit()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
        tv1.setText(name);
        tv2.setText(bred_for);
        tv3.setText(breed_group);
        tv4.setText(temperament);
        tv5.setText(origin);
        tv6.setText(life_span);

        return view;
    }

}