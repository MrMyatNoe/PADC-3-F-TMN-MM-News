package com.myanmar.tmn.news.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myanmar.tmn.news.R;
import com.myanmar.tmn.news.viewItems.ImageInNewsDetailsViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 12/10/2017.
 */

public class ImagesInNewsDetailsAdapter extends PagerAdapter {

    private List<String> mImages;

    public ImagesInNewsDetailsAdapter() {
        mImages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        /*if (object instanceof View){
            return true;
        }*/

        //return object instanceof View;
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ImageInNewsDetailsViewItem inflater = (ImageInNewsDetailsViewItem) layoutInflater.
                inflate(R.layout.item_news_details_images, container, false);
        //specific image data
        inflater.setData(mImages.get(position));
        container.addView(inflater);
        return inflater;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}