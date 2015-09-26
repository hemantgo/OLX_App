package app1.olx.com.olx_app1.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;

import app1.olx.com.olx_app1.R;

/**
 * Created by IBM_ADMIN on 9/26/2015.
 */
public class CustomPictureHolder extends LinearLayout
{
	Context mContext ;

	ImageView image1, image2, image3, image4;

	public CustomPictureHolder(Context context)
	{

		super(context);
		mContext = context;
		initializeView();
	}


	public CustomPictureHolder(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initializeView();
	}

	public CustomPictureHolder(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initializeView();

	}

	public LinearLayout getInflatedView()
	{
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		return (LinearLayout)inflater.inflate(R.layout.customimageholder, null);

	}



	public void initializeView()
	{
		this.removeAllViews();
		this.setOrientation(VERTICAL);
		this.addView(getInflatedView());
		image1 = (ImageView)this.findViewById(R.id.image1);
		image2 = (ImageView)this.findViewById(R.id.image2);
		image3 = (ImageView)this.findViewById(R.id.image3);
		image4 = (ImageView)this.findViewById(R.id.image4);

	}

	public void setImage(Uri uri, int position)
	{
		InputStream inputStream = null;
		Bitmap bitmap = null;
		try {
			inputStream = mContext.getContentResolver().openInputStream(uri);
			bitmap = BitmapFactory.decodeStream(inputStream);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(position == 1)
		{
			if(bitmap != null)
			{
				image1.setImageBitmap(bitmap);
				image1.setVisibility(VISIBLE);
				image2.setVisibility(GONE);
				image3.setVisibility(GONE);
				image4.setVisibility(GONE);
			}

		}
		else if(position == 2)
		{
			if(bitmap != null)
			{
				image2.setImageBitmap(bitmap);
				image1.setVisibility(VISIBLE);
				image2.setVisibility(VISIBLE);
				image3.setVisibility(GONE);
				image4.setVisibility(GONE);
			}
		}
		else if(position == 3)
		{
			if(bitmap != null)
			{
				image3.setImageBitmap(bitmap);
				image1.setVisibility(VISIBLE);
				image2.setVisibility(VISIBLE);
				image3.setVisibility(VISIBLE);
				image4.setVisibility(GONE);
			}
		}
		else if(position == 4)
		{
			if(bitmap != null)
			{
				image4.setImageBitmap(bitmap);
				image1.setVisibility(VISIBLE);
				image2.setVisibility(VISIBLE);
				image3.setVisibility(VISIBLE);
				image4.setVisibility(VISIBLE);
			}
		}
	}


}
