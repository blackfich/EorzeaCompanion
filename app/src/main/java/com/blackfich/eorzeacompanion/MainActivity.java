package com.blackfich.eorzeacompanion;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blackfich.eorzeacompanion.util.ui.EorzeanFragment;
import com.blackfich.eorzeacompanion.activity.about.AboutFragment;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringFragment;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringNode;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringNodesFactory;
import com.blackfich.eorzeacompanion.activity.vistas.Vista;
import com.blackfich.eorzeacompanion.activity.vistas.VistasFactory;
import com.blackfich.eorzeacompanion.activity.vistas.VistasFragment;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static List<GatheringNode> gatheringNodes;
    private static List<Vista> vistas;
    private static int eorzeaTime = -1;
    private static int prevEorzeaTime = -1;
    Timer timer = new Timer();
    private Fragment currentFragment;
    final Handler updateEorzeaClockHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            eorzeaTime = EorzeaUtils.getEorzeaTime();
            if (eorzeaTime != prevEorzeaTime) {
                TextView txtEorzeaTime = (TextView) findViewById(R.id.txtEorzeaTime);
                if (txtEorzeaTime != null) {
                    txtEorzeaTime.setText(getString("eorzea_time_short") + " " + EorzeaUtils.toTimeString(eorzeaTime));
                }
                prevEorzeaTime = eorzeaTime;
                updateFragmentTime();
            }
            return false;
        }
    });
    private AboutFragment aboutFragment;
    private GatheringFragment gatheringFragment;
    private VistasFragment vistasFragment;
    private PopupWindow popupWindow = null;

    public static int getEorzeaTime() {
        if (eorzeaTime == -1) {
            eorzeaTime = EorzeaUtils.getEorzeaTime();
        }
        return eorzeaTime;
    }

    public static List<GatheringNode> getGatheringNodes() {
        return gatheringNodes;
    }

    public static List<Vista> getVistas() {
        return vistas;
    }

    public void updateFragmentTime() {
        if (currentFragment instanceof EorzeanFragment) {
            ((EorzeanFragment) currentFragment).updateEorzeaTime();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gatheringNodes = GatheringNodesFactory.loadGatheringNodes(getAssets());
        vistas = VistasFactory.loadVistas(getAssets());

        timer = new Timer();
        timer.schedule(new UpdateEorzeaClockTask(), 0, 100);

        showFragment(getAboutFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Fragment fragment = null;
        if (id == R.id.action_about) {
            fragment = getAboutFragment();
        } else if (id == R.id.action_gathering) {
            fragment = getGatheringFragment();
        } else if (id == R.id.action_vistas) {
            fragment = getVistasFragment();
        }

        if (fragment != null) {
            showFragment(fragment);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public String getString(String name) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(name, "string", packageName);
        if (resId == 0) {
            return "{" + name + "}";
        }
        return getString(resId);
    }

    public boolean hasDrawable(String name) {
        Context context = EorzeaCompanion.getContext();
        String packageName = context.getPackageName();
        Resources resources = context.getResources();
        int resId = resources.getIdentifier(name, "drawable", packageName);
        return resId != 0;
    }

    @SuppressWarnings("deprecation")
    public Drawable getDrawable(String name) {
        Context context = EorzeaCompanion.getContext();
        String packageName = context.getPackageName();
        Resources resources = context.getResources();
        int resId = resources.getIdentifier(name, "drawable", packageName);
        if (resId == 0) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getResources().getDrawable(resId, context.getTheme());
        } else {
            return getResources().getDrawable(resId);
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.layoutMain, fragment).commit();
        currentFragment = fragment;
        updateFragmentTime();
    }

    private AboutFragment getAboutFragment() {
        if (aboutFragment == null) {
            aboutFragment = new AboutFragment();
        }
        return aboutFragment;
    }

    private GatheringFragment getGatheringFragment() {
        if (gatheringFragment == null) {
            gatheringFragment = new GatheringFragment();
        }
        return gatheringFragment;
    }

    private VistasFragment getVistasFragment() {
        if (vistasFragment == null) {
            vistasFragment = new VistasFragment();
        }
        return vistasFragment;
    }



    public View showPopup(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupLayout = inflater.inflate(layoutId, null);

        //popupWindow = new PopupWindow(popupLayout, 425, 600, true);
        popupWindow = new PopupWindow(popupLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(popupLayout, Gravity.CENTER, 0, 0);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setTouchable(true);
        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
        ImageView imgClose = (ImageView) popupLayout.findViewById(R.id.imgCloseWindow);
        if (imgClose != null) {
            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
        Button btnClose = (Button) popupLayout.findViewById(R.id.btnClose);
        if (btnClose != null) {
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
        return popupLayout;
    }

    class UpdateEorzeaClockTask extends TimerTask {
        @Override
        public void run() {
            updateEorzeaClockHandler.sendEmptyMessage(0);
        }
    }
}
