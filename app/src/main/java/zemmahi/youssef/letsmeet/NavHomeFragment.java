package zemmahi.youssef.letsmeet;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.annotation.Nullable;

/**
 * Created by mahdi on 16-03-13.
 */
public class NavHomeFragment extends Fragment{
    View homeView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle){
        homeView = inflater.inflate(R.layout.activity_nav_home, container, false);
        return homeView;
    }
}
