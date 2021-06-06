package com.serviingo.serviingo.Common;



import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.serviingo.serviingo.R;


public class CustomLoader extends Dialog {

	public CustomLoader(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomLoader(Context context, int theme) {
		super(context, theme);

		// TODO Auto-generated constructor stub
		setContentView(R.layout.custom_progress_view);



		//TextView
		//TextView tv = (TextView)findViewById(R.id.textView37);
//Glide.with(context).load(GIF_URI).into(new GlideDrawableImageViewTarget(IMAGE_VIEW));

	}

	public CustomLoader(Context context, boolean cancelable,
						OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

}
