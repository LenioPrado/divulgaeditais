package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;

import mobile.divulga.editais.ifsuldeminas.edu.br.fragment.NoticesFragment;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Results;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class ActivityHome extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Results result;

    public static int navItemIndex = 0;
    private String[] activityTitles;

    private static String CURRENT_TAG = Utils.getTagScreenRegisteredNotices();

    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    String[] listaEstados = new String[] {
            "Selecione", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        Session s = new Session(this);
        if(!s.isLoggedIn()){
            Intent i = new Intent(this, ActivityIndex.class);
            startActivity(i);
            this.finish();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        setUpNavigationView();

        loadHomeFragment();

        Log.i("navItem", "Nav item "+String.valueOf(navItemIndex));
        Log.i("navItem", "current tag "+CURRENT_TAG);
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            Intent i = new Intent(ActivityHome.this, ActivityIndex.class);
            startActivity(i);
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    private void loadHomeFragment() {
        selectNavMenu();
        setToolbarTitle();
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = null;
                int userId = new Session(getApplicationContext()).getUserId();
                Log.i("CURRENT_TAG", CURRENT_TAG);
                fragment = NoticesFragment.newUserNoticesFragmentInstance(userId, CURRENT_TAG);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        drawer.closeDrawers();
        invalidateOptionsMenu();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_cadastrados:
                        navItemIndex=0;
                        CURRENT_TAG=Utils.getTagScreenRegisteredNotices();
                        break;
                    case R.id.nav_todos:
                        navItemIndex=1;
                        CURRENT_TAG=Utils.getTagScreenAllNotices();
                        break;
                    case R.id.logout:
                        // launch new intent instead of loading fragment
                        Session s = new Session(getApplicationContext());
                        s.logoutUser();
                        startActivity(new Intent(ActivityHome.this, ActivityIndex.class));
                        drawer.closeDrawers();
                        return true;
                    default:
                        navItemIndex=1;
                        CURRENT_TAG=Utils.getTagScreenAllNotices();
                        break;
                }
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        getMenuInflater().inflate(R.menu.notifications, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.inserted_by) {
            showPopup("Órgão");
            return true;
        }

        if (id == R.id.company_type) {
            showPopup("Tipo de Empresa");
        }

        return super.onOptionsItemSelected(item);
    }

    public void showPopup(final String type){
        final List<String> filteredList;
        final String[] items;
        if(type.compareToIgnoreCase("Órgão")==0){
            filteredList = NoticesFragment.insertedByList();
        }
        else{
            filteredList = NoticesFragment.categoryList();
        }
        items = new String[filteredList.size()];
        for(int i=0; i<filteredList.size(); i++){
            items[i] = filteredList.get(i);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filtrar por "+type+":")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NoticesFragment noticesFragment = new NoticesFragment();
                        //TODO: carregar os editais com o filtro selecionado
                        //noticesFragment.setFilteredNotices(items[which], type, getApplicationContext());
                        noticesFragment.resultadoFiltro(noticesFragment.setFilteredNotices(items[which], type), getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Voce selecionou "+items[which]+"", Toast.LENGTH_LONG).show();
                    }
                });

        Dialog dialog = builder.create();
        dialog.setContentView(R.layout.desc_item);
        dialog.show();
    }



}