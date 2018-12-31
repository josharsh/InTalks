/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.liveupdates;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

/**
 * We couldn't come up with a good name for this class. Then, we realized
 * that this lesson is about RecyclerView.
 *
 * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
 * #crickets
 *
 * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
 *
 * If you don't like our puns, we named this Adapter NewsAdapter because its
 * contents are green.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NumberViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();
    private Context c;
    private int mNumberItems;

    private Context context;
    private LayoutInflater inflater;
    List<Collection> data= Collections.emptyList();
    Collection current;
    int currentPos=0;

    /**
     * Constructor for NewsAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *

     */
   // public NewsAdapter(int numberOfItems) {
    //    mNumberItems = numberOfItems;
    //}
    public NewsAdapter(Context context, List<Collection> data){
        this.c=c;
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }
    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.content;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
       // Log.d(TAG, "#" + position);
        //holder.bind(position);

        NumberViewHolder myHolder= (NumberViewHolder) holder;
        Collection current=data.get(position);
        myHolder.urlView.setText(""+current.url);
        myHolder.titleView.setText(""+current.title);
        myHolder.descView.setText(""+current.desc);
        myHolder.NumberView.setText(""+position);
      //  Glide.with(c).load(""+current.urlT)
        //        .into(myHolder.ivFishh);








        /*myHolder.textSize.setText("Size: " + current.sizeName);
        myHolder.textType.setText("Category: " + current.catName);
        myHolder.textPrice.setText("Rs. " + current.price + "\\Kg"); */
        //myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView,urlView,titleView,descView,NumberView;
         ImageView ivFishh;
        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link NewsAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            super(itemView);

           // listItemNumberView = (TextView) itemView.findViewById(R.id.tt);
            urlView= (TextView) itemView.findViewById(R.id.url);
           descView = (TextView) itemView.findViewById(R.id.description);
           NumberView = (TextView) itemView.findViewById(R.id.number);
           titleView=(TextView)itemView.findViewById(R.id.tit);
      //     ivFishh=(ImageView)itemView.findViewById(R.id.ivFish) ;


        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
      //  void bind(int listIndex) {
                      // listItemNumberView.setText(listIndex);


        //}
    }
}
