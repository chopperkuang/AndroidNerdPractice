package net.kkuang.example.criminalintent;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;

import java.util.UUID;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID)getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

}
