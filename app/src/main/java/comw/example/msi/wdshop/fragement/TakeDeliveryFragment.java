package comw.example.msi.wdshop.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import comw.example.msi.wdshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeDeliveryFragment extends Fragment {


    public TakeDeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_take_delivery, container, false);
    }

}
