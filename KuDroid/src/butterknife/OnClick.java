package butterknife;

import android.view.View.OnClickListener;

public @interface OnClick {
	int value();

	Class<?> type() default OnClickListener.class;
}
