package com.ladwa.aditya.popularmovies.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ladwa.aditya.popularmovies.R;
import com.ladwa.aditya.popularmovies.data.model.ResultListModel;
import com.ladwa.aditya.popularmovies.util.Utility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    private static final String LOG_TAG = MovieDetailActivityFragment.class.getSimpleName();


    @Bind(R.id.imageposter)
    ImageView imgPoster;
    @Bind(R.id.releasedate)
    TextView tvReleaseDate;
    @Bind(R.id.rating)
    TextView tvRating;
    @Bind(R.id.plot)
    TextView tvPlot;
    ImageView imgBackdrop;
    ResultListModel.ResultModel model;
    private onFragmentInteraction mListener;


    public MovieDetailActivityFragment() {
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mListener = (onFragmentInteraction) getActivity();
        mListener.setActionBarTitle(model.getOriginalTitle());
        mListener.setBackdropImage(Utility.URL_IMAGE_BACKDROP_BASE + model.getBackdropUrl());

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);


        imgBackdrop = (ImageView) getActivity().findViewById(R.id.toolbar_image_backdrop);

        model = getActivity().getIntent().getParcelableExtra(Utility.EXTRA_RESULT_MODEL);
        String url = Utility.URL_IMAGE_BASE + model.getPosterUrl();
        //  tvTitle.setText(model.getOriginalTitle());
        tvReleaseDate.setText(String.format(getString(R.string.release_date), model.getReleaseDate()));
        tvRating.setText(String.format(getString(R.string.rating), model.getRating()));
        tvPlot.setText(model.getPlot());
        Glide.with(getContext())
                .load(url)
                .error(R.drawable.poster)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPoster);

        return view;
    }


    public interface onFragmentInteraction {
        void setActionBarTitle(String title);

        void setBackdropImage(String backdropUrl);

    }
}