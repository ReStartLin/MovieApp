package restart.com.movieapp.Video;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import restart.com.movieapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FgVideoFragment extends Fragment {


    public FgVideoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fg_video, container, false);
    }

}
