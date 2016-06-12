package butterknife;


import android.view.View.OnClickListener;

public @interface Bind{
	int value();

	Class<?> type() default OnClickListener.class;
	
	
}