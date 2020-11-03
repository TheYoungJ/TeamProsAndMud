package com.example.demosearch;

import android.app.Activity;
import android.content.Context;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter implements Filterable {
    private Context context;
    //2D array to store names and pic urls
    //items: names is 0 and pics are 1
    //original_items.get(position).get(0) for getting the name of an item
    private ArrayList<ArrayList<String>> original_items;
    private ArrayList<ArrayList<String>> temp_items;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private Activity activity;

    public ImageAdapter(Context context, ArrayList<ArrayList<String>> items) {
        this.context = context;
        this.original_items = items;
        temp_items = original_items;
        /*this.options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.oneforall)
                .showImageForEmptyUri(R.mipmap.oneforall)
                .showImageOnFail(R.mipmap.oneforall)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();*/

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
    }

    public void updateView(ArrayList<ArrayList<String>> update){
        this.temp_items = update;
        System.out.println("UPDATED!!\n");
        System.out.println("TEMP AFTER UPDATE: "+temp_items+"\n");
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mobile, null);

            System.out.println("TEMP @Pos: "+position+"\n");
            System.out.println("GET NAME: "+temp_items.get(position).get(0));
            System.out.println("PIC: "+temp_items.get(position).get(1));
            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(temp_items.get(position).get(0));

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = temp_items.get(position).get(0);

            //get images from urls in pics
            if(temp_items.get(position).get(1) != null) {
                String url = temp_items.get(position).get(1);
                final Bitmap[] ma = new Bitmap[1];
                imageLoader.loadImage(url, new SimpleImageLoadingListener(){
                    @Override
                    public void onLoadingComplete(String imageUri, View view,
                                                  Bitmap loadedImage) {
                        super.onLoadingComplete(imageUri, view, loadedImage);
                        ma[0] = loadedImage;
                        //write your code here to use loadedImage
                    }
                });

                //ImageSize targetSize = new ImageSize(200, 200); // result Bitmap will be fit to this size
                //Bitmap bmp = imageLoader.loadImageSync(pics.get(position), targetSize, options);
                //Bitmap bmp = imageLoader.loadImageSync(pics.get(position));

                imageView.setImageBitmap(ma[0]);
            }else{
                imageView.setImageResource(R.mipmap.oneforall);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                temp_items=(ArrayList<ArrayList<String>>)results.values;
                //System.out.println("TEMP ITEMS AFTER FILTER: "+temp_items);
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<ArrayList<String>> FilteredList = new ArrayList<ArrayList<String>>();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = original_items;
                    results.count = original_items.size();
                } else {
                    //filter for results
                    for (int i = 0; i < original_items.size(); i++) {
                        //search by name @ .get(0)
                        String data = original_items.get(i).get(0);
                        if (data.toLowerCase().contains(constraint.toString())) {
                            ArrayList<String> all = new ArrayList<>(2);
                            all.add(0, data);
                            all.add(1, original_items.get(i).get(1));
                            FilteredList.add(all);
                        }
                    }
                    results.values = FilteredList;
                    results.count = FilteredList.size();
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public int getCount() {
        return original_items.size();
    }

    @Override
    public Object getItem(int position) {
        return original_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

/*public class filter_here extends Filter {

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        // TODO Auto-generated method stub

        FilterResults Result = new FilterResults();
        // if constraint is empty return the original names
        if(constraint.length() == 0 ){
            Result.values = Original_Names;
            Result.count = Original_Names.size();
            return Result;
        }

        ArrayList<String> Filtered_Names = new ArrayList<String>();
        String filterString = constraint.toString().toLowerCase();
        String filterableString;

        for(int i = 0; i<Original_Names.size(); i++){
            filterableString = Original_Names.get(i);
            if(filterableString.toLowerCase().contains(filterString)){
                Filtered_Names.add(filterableString);
            }
        }
        Result.values = Filtered_Names;
        Result.count = Filtered_Names.size();

        return Result;
    }

    @Override
    protected void publishResults(CharSequence constraint,FilterResults results) {
        // TODO Auto-generated method stub
        Names = (ArrayList<String>) results.values;
        notifyDataSetChanged();
    }

}*/

/*
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            //these will be place holder white images to match background
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background,
            R.drawable.oneforall_background, R.drawable.oneforall_background
    };
}*/


