package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
//        ArrayList<Integer> mImageIds = new ArrayList<>(Arrays.asList(
//                .drawable.1,R.drawable.2,R.drawable.3,R.drawable.4,
//        R.drawable.5,R.drawable.6,R.drawable.7,R.drawable.8,
//        R.drawable.9
//                ));
ArrayList<Integer> mImageIds = new ArrayList < >(Arrays.asList(
        R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,
        R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,
        R.drawable.img9 //show picture is in activity xml

));

//custom diaglog show video results

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                GridView gridView = findViewById(R.id.myGrid);
                gridView.setAdapter(new ImageAdaptor(mImageIds, this));

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                int item_pos = mImageIds.get(position);
                                showDialogBox(item_pos);// show each image information
                        }// desgin each images
                });


        }

        public void showDialogBox(int item_pos){
                Dialog dialog = new Dialog( this);
                dialog.setContentView(R.layout.custom_dialog);

                TextView Image_name = dialog.findViewById(R.id.txt_Image_name);
                ImageView Image = dialog.findViewById(R.id.img);
                Button btn_Full = dialog.findViewById(R.id.btn_full);// full image touch and
                Button btn_Close = dialog.findViewById(R.id.btn_close);// close imarge view

                String title = getResources().getResourceName(item_pos);

                //extracting name
                int index = title.indexOf("/");
                String name = title.substring(index+1,title.length());
                Image_name.setText(name);

                Image.setImageResource(item_pos);

                btn_Close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                dialog.dismiss();
                        }
                });

                btn_Full.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                         Intent i = new Intent(Intent.makeMainActivity.this, FullView.class);
                         i.putExtra("img_id",item_pos);
                         startActivity(i);
                        }
                });

                dialog.show();
        }
}