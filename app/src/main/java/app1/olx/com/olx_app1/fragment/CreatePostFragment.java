package app1.olx.com.olx_app1.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app1.olx.com.olx_app1.Objects.PostDO;
import app1.olx.com.olx_app1.R;
import app1.olx.com.olx_app1.custom.CustomEditText;
import app1.olx.com.olx_app1.custom.DataBasehelper;

public class CreatePostFragment extends Fragment {

	DataBasehelper dbHelper;
	CustomEditText ceTitle, ceDescription, ceCategory, ceLocation, ceName, ceEmailAddress, cePhoneNumber;
	Button btnPost;
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

	    ceEmailAddress = (CustomEditText)rootView.findViewById(R.id.ceEmailAddress);
	    cePhoneNumber = (CustomEditText)rootView.findViewById(R.id.cePhoneNumber);

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
		    }
	    });

	    return rootView;
    }



}