package com.example.demosearch;

import android.content.Context;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> names;
    private ArrayList<String> pics;
    //private final DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();


    public ImageAdapter(Context context, ArrayList<String> names, ArrayList<String> pics) {
        this.context = context;
        this.names = names;
        this.pics = pics;
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

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.mobile, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(names.get(position));

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = names.get(position);

            //get images from urls in pics
            if(pics.get(position) != null) {
                String url = pics.get(position);
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

            //change how image searches are done, maybe send pairs
            /*if (mobile.equals("Windows")) {
                imageView.setImageResource(R.drawable.windows_logo);
            } else if (mobile.equals("iOS")) {
                imageView.setImageResource(R.drawable.ios_logo);
            } else if (mobile.equals("Blackberry")) {
                imageView.setImageResource(R.drawable.blackberry_logo);
            } else {
                imageView.setImageResource(R.drawable.android_logo);
            }*/

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

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


