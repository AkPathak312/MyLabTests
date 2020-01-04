package com.example.mylabtests;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class UserDashboard extends AppCompatActivity {

    FirebaseAuth auth;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    int count=0;
   // MaterialSearchBar searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        navigationView=findViewById(R.id.navview);
        drawerLayout=findViewById(R.id.drwawerlayout);
        //getSupportActionBar();
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,0,0);
       // drawerLayout.addDrawerListener(actionBarDrawerToggle);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dashboard:
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.logout:
                        drawerLayout.closeDrawers();
                        AlertDialog.Builder aBuilder=new AlertDialog.Builder(UserDashboard.this);
                        aBuilder.setTitle("Are you Sure?");
                        aBuilder.setMessage("Logging out will be take you out of your app and further use of app will require logging in again.");
                        aBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog alertDialog=aBuilder.create();
                        alertDialog.show();
                        break;

                    case R.id.menulab:
                        drawerLayout.closeDrawers();
                        Intent i=new Intent(UserDashboard.this,LabTests.class);
                        startActivity(i);
                       // finish();
                        break;

                    case R.id.menurecord:
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(UserDashboard.this,MyRecords.class));
                       // finish();
                        break;

                    case R.id.menusett:
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(UserDashboard.this,AboutRuban.class));
                        break;
                      //  FirebaseAuth.getInstance().signOut();
                }


                return true;
            }
        });

        auth=FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                // Toast.makeText(MainActivity.this, "Checking", Toast.LENGTH_SHORT).show();
                if(firebaseUser!=null){
                }
                else{
                    finish();
                }
            }
        });
    }


    //Code for Going to Health Package
    public void gotohealth(View view) {

        Intent i=new Intent(UserDashboard.this,HealthPackages.class);
        startActivity(i);
    }

    public void gotobookappointment(View view) {

        Intent i=new Intent(UserDashboard.this,BookAppointments.class);
        startActivity(i);
    }

    public void gotoAboutUS(View view) {
        Intent i=new Intent(UserDashboard.this,AboutRuban.class);
        startActivity(i);
    }

    public void gotolabtest(View view) {

        Intent i=new Intent(UserDashboard.this,LabTests.class);
        startActivity(i);
    }

    public void gotoMyRecords(View view) {

        Intent i=new Intent(UserDashboard.this,RecordsDashboard.class);
        startActivity(i);
    }

    public void gotoSettings(View view) {
        SettingsBottomSheet settingsBottomSheet=new SettingsBottomSheet();
        settingsBottomSheet.show(getSupportFragmentManager(),"settings");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(count==1){
            System.exit((0));
        }
        else {
            count++;
            Toast.makeText(UserDashboard.this, "Click Again to Exit.", Toast.LENGTH_SHORT).show();
        }
    }

}
