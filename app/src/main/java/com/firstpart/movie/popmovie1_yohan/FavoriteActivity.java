package com.firstpart.movie.popmovie1_yohan;

import android.content.Context;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;


import android.database.Cursor;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;



/**
 * Created by Yohan on 10/05/2018.
 */


public class FavoriteActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>, FavoritesAdapter.FavClickListener {

    private Menu mMenu;

    private static final String TAG = "MyActivity";
    private FavoritesAdapter adapter;
    RecyclerView favsRecyclerView;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportLoaderManager().initLoader(0, null, this);

        favsRecyclerView = findViewById(R.id.fav_rec_view);
        favsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FavoritesAdapter(this, null, this);
        favsRecyclerView.setAdapter(adapter);




    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, mMenu);




        // Make menu items accessible
        mMenu = menu;

        //ADD DELETE FAVORITE ICON
        mMenu.add(Menu.NONE, R.string.pref_delete_favorite, Menu.NONE, null)
                .setVisible(true)
                .setIcon(R.drawable.ic_delete_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //CONTEXT FOR THE TOAST
        Context context = getApplicationContext();

        switch (item.getItemId()) {
            case R.string.pref_delete_favorite:

                Toast.makeText(context, "ALL FAVORITE MOVIES WERE DELETED", Toast.LENGTH_LONG).show();
                getContentResolver().delete(FavoriteContract.MovieEntry.CONTENT_URI, null, null);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, FavoriteContract.MovieEntry.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public void onFavItemClick(Movie movie) {





    }








}
