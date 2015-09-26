package app1.olx.com.olx_app1.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import net.i2p.android.ext.floatingactionbutton.FloatingActionsMenu;

import java.io.FileNotFoundException;
import java.io.InputStream;

import app1.olx.com.olx_app1.Objects.PostDO;
import app1.olx.com.olx_app1.R;
import app1.olx.com.olx_app1.custom.CustomEditText;
import app1.olx.com.olx_app1.custom.CustomPictureHolder;
import app1.olx.com.olx_app1.custom.DataBasehelper;

public class CreatePostFragment extends Fragment {

	DataBasehelper dbHelper;
	CustomEditText ceTitle, ceDescription, ceCategory, ceLocation, ceName, ceEmailAddress, cePhoneNumber;
	CustomPictureHolder pictureHolder;
	AutoCompleteTextView autocomplete_country, autocomplete_category;
	Button btnPost;
	FloatingActionsMenu CapturePictures;

	public int imagePos = 1;

	private static final int SELECT_PICTURE = 1000;
    public CreatePostFragment()
    {

    }

    public static CreatePostFragment getInstance()
    {
	    CreatePostFragment objAccountSettingFragment = new CreatePostFragment();

        return objAccountSettingFragment;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        } catch (ClassCastException castException) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

	    dbHelper = new DataBasehelper(getActivity());

	    dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

	    View rootView = inflater.inflate(R.layout.fragment_create_post, container, false);

	    ceTitle = (CustomEditText)rootView.findViewById(R.id.ceTitle);
	    ceDescription = (CustomEditText)rootView.findViewById(R.id.ceDescription);
	    ceCategory = (CustomEditText)rootView.findViewById(R.id.ceCategory);
	    ceName = (CustomEditText)rootView.findViewById(R.id.ceName);
	    ceLocation = (CustomEditText)rootView.findViewById(R.id.ceLocation);
	    btnPost = (Button)rootView.findViewById(R.id.btnPost);
	    CapturePictures = (FloatingActionsMenu)rootView.findViewById(R.id.CapturePictures);
	    ceEmailAddress = (CustomEditText)rootView.findViewById(R.id.ceEmailAddress);
	    cePhoneNumber = (CustomEditText)rootView.findViewById(R.id.cePhoneNumber);
	    pictureHolder= (CustomPictureHolder)rootView.findViewById(R.id.pictureHolder);

	    autocomplete_country = (AutoCompleteTextView)rootView.findViewById(R.id.autocomplete_country);
	    autocomplete_category = (AutoCompleteTextView)rootView.findViewById(R.id.autocomplete_category);
	    autocomplete_country.setText("Delhi");
	    autocomplete_category.setText("Mobile - Android");

	    String[] countries = getResources().getStringArray(R.array.city);
// Create the adapter and set it to the AutoCompleteTextView
	    ArrayAdapter<String> adapter =
			    new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, countries);
	    autocomplete_country.setAdapter(adapter);


	    // Get a reference to the AutoCompleteTextView in the layout
	    String[] Category = getResources().getStringArray(R.array.category);
// Create the adapter and set it to the AutoCompleteTextView
	    ArrayAdapter<String> adapterCategory =
			    new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Category);
	    autocomplete_category.setAdapter(adapterCategory);


	    pictureHolder.setVisibility(View.GONE);

	    ceTitle.setHint("Title");
	    ceDescription.setHint("Description");
	    ceCategory.setHint("Category");
	    ceLocation.setHint("Location");
	    ceName.setHint("Name");
	    ceEmailAddress.setHint("EmailAddress");
	    cePhoneNumber.setHint("Phone Number");

	    ceCategory.setEditable(false);

	    cePhoneNumber.setNumeric();

	    ceDescription.setTextWatcher(10, 50);
		ceDescription.setMultipleLine(5);

	    ceLocation.setEditable(false);
	    ceLocation.setRightBoundImage();

	    CapturePictures.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
		    @Override
		    public void onMenuExpanded() {
			    Intent pickIntent = new Intent();
			    pickIntent.setType("image/*");
			    pickIntent.setAction(Intent.ACTION_GET_CONTENT);

			    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			    String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
			    Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
			    chooserIntent.putExtra
					    (
							    Intent.EXTRA_INITIAL_INTENTS,
							    new Intent[] { takePhotoIntent }
					    );

			    CapturePictures.collapse();
			    startActivityForResult(chooserIntent, SELECT_PICTURE);
		    }

		    @Override
		    public void onMenuCollapsed() {
		    }
	    });
	    CapturePictures.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			    Intent pickIntent = new Intent();
			    pickIntent.setType("image/*");
			    pickIntent.setAction(Intent.ACTION_GET_CONTENT);

			    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			    String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
			    Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
			    chooserIntent.putExtra
					    (
							    Intent.EXTRA_INITIAL_INTENTS,
							    new Intent[] { takePhotoIntent }
					    );

			    startActivityForResult(chooserIntent, SELECT_PICTURE);
		    }
	    });


	    btnPost.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			    PostDO postDO = new PostDO();
			    postDO.title = ceTitle.getText();
			    postDO.description = ceDescription.getText();
			    postDO.category = ceCategory.getText();
			    postDO.location = ceLocation.getText();
			    postDO.name = ceName.getText();
			    postDO.email = ceEmailAddress.getText();
			    postDO.phone_number = cePhoneNumber.getText();

			    dbHelper.postAD(postDO);
			    Toast.makeText(getActivity(), "Posted it!", Toast.LENGTH_LONG).show();
		    }
	    });

	    return rootView;
    }


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.e("onActivityResult"   ,"Fragment");
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
			if (data == null) {
				return;
			}
			try {
				Uri strData = data.getData();
				pictureHolder.setVisibility(View.VISIBLE);
				pictureHolder.setImage(strData, imagePos);
				imagePos++;
				if(imagePos > 4)
				{
					CapturePictures.setVisibility(View.GONE);
				}

//				InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
//				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//				imgPhoto.setImageBitmap(bitmap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}