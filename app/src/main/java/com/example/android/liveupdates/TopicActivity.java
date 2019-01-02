package com.example.android.liveupdates;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {
    private static final String SEARCH_QUERY_URL_EXTRA = "https://newsapi.org/v2/everything?q=sports&apiKey=76c88fc1af2d4ec5ad01da0235754861";
//private static final int number_of_items=100;
private NewsAdapter mAdapter;
private RecyclerView mList;
//TextView mStringTextView;
//ImageView ii;
//static String JsonData;
    private static final int LOADER = 22;
    private ProgressBar mLoadingIndicator;
    private EditText mSearchBoxEditText;
    private TextView mSearchResultsTextView,mUrlDisplayTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        //mList=(RecyclerView)findViewById(R.id.ViewR);
      //  LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //mList.setLayoutManager(layoutManager);
        //mList.setHasFixedSize(true);
      //  mAdapter=new NewsAdapter(number_of_items);
       // mList.setAdapter(mAdapter);
        mSearchBoxEditText=(EditText)findViewById(R.id.editT);
       // ii=(ImageView)findViewById(R.id.i);
        mLoadingIndicator=(ProgressBar)findViewById(R.id.pb_loading_indicator);
        mSearchResultsTextView=(TextView)findViewById(R.id.Res);
        mUrlDisplayTextView=(TextView)findViewById(R.id.Res2);
     //   mStringTextView=(TextView)findViewById(R.id.ResNew);
        Button SearchBtn=(Button)findViewById(R.id.searchbtn);

        SearchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                makeGithubSearchQuery();
                //showImage();
            }
        });
        if (savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA);
            // COMPLETED (26) Remove the code that retrieves the JSON

            mUrlDisplayTextView.setText(queryUrl);
            // COMPLETED (25) Remove the code that displays the JSON
        }

        // COMPLETED (24) Initialize the loader with GITHUB_SEARCH_LOADER as the ID, null for the bundle, and this for the context
        /*
         * Initialize the loader
         */
        getSupportLoaderManager().initLoader(LOADER, null, this);
    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally request that an AsyncTaskLoader performs the GET request.
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        if (TextUtils.isEmpty(githubQuery)) {
            Toast.makeText(this,"No query Entered, Nothing to search for",Toast.LENGTH_LONG).show();
           // mUrlDisplayTextView.setText("No query entered, nothing to search for.");
            return;
        }

        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        // COMPLETED (18) Remove the call to execute the AsyncTask

        // COMPLETED (19) Create a bundle called queryBundle
        Bundle queryBundle = new Bundle();
        // COMPLETED (20) Use putString with SEARCH_QUERY_URL_EXTRA as the key and the String value of the URL as the value
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, githubSearchUrl.toString());

        LoaderManager loaderManager = getSupportLoaderManager();
        // COMPLETED (22) Get our Loader by calling getLoader and passing the ID we specified
        Loader<String> githubSearchLoader = loaderManager.getLoader(LOADER);
        // COMPLETED (23) If the Loader was null, initialize it. Else, restart it.
        if (githubSearchLoader == null) {
            loaderManager.initLoader(LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(LOADER, queryBundle, this);
        }
    }

    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        /* Then, show the error */

    }
    //public static String getData(){
      //  return JsonData;
    //}
    // COMPLETED (3) Override onCreateLoader
    @Override
    public  Loader<String> onCreateLoader(int id, final Bundle args) {
        // COMPLETED (4) Return a new AsyncTaskLoader<String> as an anonymous inner class with this as the constructor's parameter
        return new AsyncTaskLoader<String>(this) {

            // COMPLETED (5) Override onStartLoading
            @Override
            protected void onStartLoading() {

                // COMPLETED (6) If args is null, return.
                /* If no arguments were passed, we don't have a query to perform. Simply return. */
                if (args == null) {
                    return;
                }

                // COMPLETED (7) Show the loading indicator
                /*
                 * When we initially begin loading in the background, we want to display the
                 * loading indicator to the user
                 */
                mLoadingIndicator.setVisibility(View.VISIBLE);
               // Toast.makeText(TopicActivity.this,"Hello",Toast.LENGTH_SHORT).show();
                forceLoad();


            }

            // COMPLETED (9) Override loadInBackground
            @Override
            public String loadInBackground() {

                // COMPLETED (10) Get the String for our URL from the bundle passed to onCreateLoader
                /* Extract the search query from the args using our constant */
                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);

                // COMPLETED (11) If the URL is null or empty, return null
                /* If the user didn't enter anything, there's nothing to search for */
                if (searchQueryUrlString == null || TextUtils.isEmpty(searchQueryUrlString)) {
                    return null;
                }

                // COMPLETED (12) Copy the try / catch block from the AsyncTask's doInBackground method
                /* Parse the URL from the passed in String and perform the search */
                try {
                    URL githubUrl = new URL(searchQueryUrlString);
                    String githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubUrl);

                   //startActivity(i);
                    return githubSearchResults;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    // COMPLETED (13) Override onLoadFinished
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        // COMPLETED (14) Hide the loading indicator
        /* When we finish loading, we want to hide the loading indicator from the user. */
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT);
        List<Collection> Listdata=new ArrayList<>();
        // COMPLETED (15) Use the same logic used in onPostExecute to show the data or the error message
        /*
         * If the results are null, we assume an error has occurred. There are much more robust
         * methods for checking errors, but we wanted to keep this particular example simple.
         */
        if (null == data) {
            showErrorMessage();
        } else {
            mSearchResultsTextView.setText(data);
            try {
                JSONObject obj= new JSONObject(data);
                String numberOfResults=obj.getString("totalResults");
                JSONArray articles= obj.getJSONArray("articles");
               // JSONObject obj2=articles.getJSONObject(0);
               /* String title=obj2.getString("title");
                String desc=obj2.getString("description");
                String url=obj2.getString("url");*/

                for(int i=0;i<articles.length();i++){
                    JSONObject json_data = articles.getJSONObject(i);
                    Collection NewsData = new Collection();
                    NewsData.url= json_data.getString("url");
                    //Toast.makeText(this, "URL added", Toast.LENGTH_SHORT).show();
                    NewsData.title=json_data.getString("title");
                    NewsData.desc=json_data.getString("description");
                    NewsData.urlTT=new URL(json_data.getString("urlToImage"));


                   // Glide.with(getBaseContext()).load(uurlT).into(NewsData.imgg);
                    //Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, ""+NewsData.url, Toast.LENGTH_SHORT).show();
                  //  NewsData.Number=i;
                    /*fishData.fishName= json_data.getString("fish_name");
                    fishData.catName= json_data.getString("cat_name");
                    fishData.sizeName= json_data.getString("size_name");
                    fishData.price= json_data.getInt("price");*/
                    Listdata.add(NewsData);
                }

                // Setup and Handover data to recyclerview
              //  mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);
                mAdapter = new NewsAdapter(TopicActivity.this, Listdata);
                mList=(RecyclerView)findViewById(R.id.ViewR);
                mList.setAdapter(mAdapter);
               // Toast.makeText(this,""+Listdata.get(4),Toast.LENGTH_SHORT);
               // Toast.makeText(this, Listdata.size()+"", Toast.LENGTH_SHORT).show();
                mList.setLayoutManager(new LinearLayoutManager(TopicActivity.this));


            } catch (Exception e) {
                Log.v("","HHHHHHHHHH");

                Toast.makeText(TopicActivity.this, "h"+e.toString(), Toast.LENGTH_LONG).show();
            }


        }
    }

    // COMPLETED (16) Override onLoaderReset as it is part of the interface we implement, but don't do anything in this method
    @Override
    public void onLoaderReset(Loader<String> loader) {
        /*
         * We aren't using this method in our example application, but we are required to Override
         * it to implement the LoaderCallbacks<String> interface
         */
    }

    // COMPLETED (29) Delete the AsyncTask class






    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String queryUrl = mUrlDisplayTextView.getText().toString();
        outState.putString(SEARCH_QUERY_URL_EXTRA, queryUrl);

        // COMPLETED (27) Remove the code that persists the JSON
    }
}