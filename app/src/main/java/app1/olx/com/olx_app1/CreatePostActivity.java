package app1.olx.com.olx_app1;

import android.content.Intent;
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


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);



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
