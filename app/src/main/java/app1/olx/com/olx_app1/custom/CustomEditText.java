package app1.olx.com.olx_app1.custom;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.LayoutDirection;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app1.olx.com.olx_app1.R;

/**
 * Created by IBM_ADMIN on 9/26/2015.
 */
public class CustomEditText extends LinearLayout
{
	Context mContext ;
	EditText etTitle;
	TextInputLayout tilTitle;
	RelativeLayout errorLayout;
	TextView errorText;


	public CustomEditText(Context context)
	{

		super(context);
		mContext = context;
		initializeView();
	}


	public CustomEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initializeView();
	}

	public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initializeView();

	}

	public LinearLayout getInflatedView()
	{
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		return (LinearLayout)inflater.inflate(R.layout.customedittext, null);

	}



	public void initializeView()
	{
		this.removeAllViews();
		this.setOrientation(VERTICAL);
		this.addView(getInflatedView());
		etTitle = (EditText)this.findViewById(R.id.etTitle);
		tilTitle = (TextInputLayout)this.findViewById(R.id.tilTitle);
		errorLayout = (RelativeLayout)this.findViewById(R.id.errorLayout);
		errorText = (TextView)this.findViewById(R.id.errorText);

		etTitle.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
				if(!charSequence.toString().equalsIgnoreCase(""))
					setError("");
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});

		errorLayout.setVisibility(GONE);
	}

	public void setError(String error)
	{
		if(!error.equalsIgnoreCase(""))
		{
			errorLayout.setVisibility(VISIBLE);
			errorText.setText(error);
		}
		else
		{
			errorLayout.setVisibility(GONE);
		}
	}


	public void setHint(String hint)
	{
		tilTitle.setHint(hint);

	}

	public void setNumeric()
	{
//		etTitle.setMaxEms(10);

		etTitle.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
		etTitle.setInputType(InputType.TYPE_CLASS_NUMBER);
	}

	public void setText(String Text)
	{
		etTitle.setText(Text);
	}

	public void setMultipleLine(int lines)
	{
		etTitle.setSingleLine(false);
		etTitle.setMaxLines(lines);
	}

	public void setEditable(Boolean isEditable)
	{
		etTitle.setEnabled(isEditable);
		etTitle.setFocusable(isEditable);
		etTitle.setActivated(isEditable);
		etTitle.setClickable(!isEditable);
	}

	public void setRightBoundImage()
	{
		etTitle.setCompoundDrawablesRelative(null, null, null, mContext.getResources().getDrawable(R.drawable.ic_launcher));
	}

	public void setTextWatcher(int min, int max)
	{
		etTitle.addTextChangedListener(new CharacterCountErrorWatcher(tilTitle, min, max));
	}

	public EditText getEditText()
	{
		return etTitle;
	}

	public String getText()
	{
		return etTitle.getText().toString();
	}


	public class CharacterCountErrorWatcher
			implements TextWatcher
	{
		private final TextInputLayout mTextInputLayout;
		private final ForegroundColorSpan mNormalTextAppearance;
		private final AlignmentSpan mAlignmentSpan = new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE);
		private final SpannableStringBuilder mErrorText = new SpannableStringBuilder();
		private int mMinLen;
		private int mMaxLen;

		public CharacterCountErrorWatcher(TextInputLayout textInputLayout, int minLen, int maxLen)
		{
			mTextInputLayout = textInputLayout;
			mNormalTextAppearance = new ForegroundColorSpan(Color.GRAY);
			mMinLen = minLen;
			mMaxLen = maxLen;
			updateErrorText();
		}

		private void updateErrorText()
		{
			mErrorText.clear();
			mErrorText.clearSpans();
			final int length = mTextInputLayout.getEditText().length();
			if(length > 0){
				mErrorText.append(String.valueOf(length));
				mErrorText.append(" / ");
				mErrorText.append(String.valueOf(mMaxLen));
				mErrorText.setSpan(mAlignmentSpan, 0, mErrorText.length(),
						Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
				if(hasValidLength()){
					mErrorText.setSpan(mNormalTextAppearance, 0, mErrorText.length(),
							Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
				}
			}
			mTextInputLayout.setError(mErrorText);
		}

		public boolean hasValidLength()
		{
			final int length = mTextInputLayout.getEditText().length();
			return (length >= mMinLen && length <= mMaxLen);
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			updateErrorText();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			//
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			//
		}
	}


}
