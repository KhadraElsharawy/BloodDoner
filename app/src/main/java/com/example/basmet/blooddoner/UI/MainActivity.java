package com.example.basmet.blooddoner.UI;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.basmet.blooddoner.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionbar.setTitle("");
        actionbar.setIcon(R.drawable.ic_notifications_white_24dp);
        setUpDrawableContent(navigationView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
     public void selectItemDrawer(MenuItem menuItem){
         Fragment fragment = null;
         Class fragmentClass;
         switch (menuItem.getItemId()){
             case R.id.nav_profile :
                 fragmentClass = ProfileFragment.class;
                 break;
           //  case  R.id.nav_request :
           //     break;
             case R.id.nav_help :
                 fragmentClass = HelpFragment.class;
                 break;
             case R.id.nav_settings :
                 fragmentClass = SettingsFragment.class;
                 break;
             default :
                 fragmentClass = ProfileFragment.class;
                 break;
         }
         try {
             fragment = (Fragment) fragmentClass.newInstance();
         } catch (Exception e) {
             e.printStackTrace();
         }
         FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
         menuItem.setChecked(true);
         setTitle(menuItem.getTitle());
         drawerLayout.closeDrawers();
     }

     private void setUpDrawableContent (NavigationView navigationView){
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 selectItemDrawer(item);
                 return true;
             }
         });
     }
}
