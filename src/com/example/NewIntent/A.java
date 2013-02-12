package com.example.NewIntent;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Date;

public class A extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Get the intent, verify the action and get the query
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        // Source: https://developer.android.com/guide/topics/search/search-dialog.html#ConfiguringWidget

        return true;
    }

    /**
     * Because of singletop and search, we support onNewIntent
     * This occurs when the search activity is already running _but_ the user has requested a new search.  Singletop
     * ensures that there is only one search activity on the back stack...and this is executed because we are _not_
     * creating a new activity.
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        /* we want future calls to getIntent have this new intent. e.g. new search not the one at creation */
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (!Intent.ACTION_SEARCH.equals(intent.getAction())) {
            getActionBar().setTitle("!search");
            //getLoaderManager().initLoader(LOADER_PROPERTY, null, mLoaderCallbacks);
        } else {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getActionBar().setTitle(query);
            ((TextView) findViewById(R.id.text)).setText(query);
            //search(query);
        }
    }

}
