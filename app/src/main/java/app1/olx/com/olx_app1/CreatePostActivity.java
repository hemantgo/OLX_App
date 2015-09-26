package app1.olx.com.olx_app1;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import app1.olx.com.olx_app1.custom.DataBasehelper;
import app1.olx.com.olx_app1.fragment.CreatePostFragment;


public class CreatePostActivity extends ActionBarActivity {


	double[] lat = new double[]{28.441800, 28.527915, 28.669259, 28.407669};
	double [] longitude = new double[]{76.987000, 77.389547, 77.106310, 77.315984};
	String [] city = new String[]{"Gurgaon", "Noida", "Delhi", "Faridabad"};


	public String selectedLocation;
	public Location location;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);

		LocationManager mgr = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean network_enabled = mgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		if(network_enabled)
		{
			location = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}

		if(location != null)
		{
			for (int i = 0 ; i < lat.length ; i++)
			{
				Location loc = new Location("");
				loc.setLatitude(lat[i]);
				loc.setLongitude(longitude[i]);

				if(location.distanceTo(loc) < 50000)
				{
					selectedLocation = city[i];
					break;
				}
			}

			if(selectedLocation.equalsIgnoreCase(""))
			{
				selectedLocation = "Delhi";
			}
		}

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new CreatePostFragment())
					.commit();
		}




	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.e("onActivityResult", "Activity");
		super.onActivityResult(requestCode, resultCode, data);
	}


}
