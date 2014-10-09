package net.kkuang.example.criminalintent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class SingleFragmentActivity extends Activity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit();
        }
    }
}
