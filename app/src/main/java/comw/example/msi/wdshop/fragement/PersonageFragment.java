package comw.example.msi.wdshop.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import comw.example.msi.wdshop.R;
import comw.example.msi.wdshop.presenter.IPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonageFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personage, container, false);
    }

}
