package butterknife;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import android.app.Activity;
import android.view.View;
/**
 * 用法和ButterKnife一样
 * 
 * @author houen.bao
 *
 */
public class ButterKnife {

	private final static String TAG = "ButterKnife";

	private static boolean DEBUG = false;

	public static void setDebug(boolean isDebug) {
		DEBUG = isDebug;
	}

	public static void bind(Object obj) {
		bind(obj, null);
	}

	public static void bind(Object obj, View rootView) {
		if (obj == null) {
			return;
		}
		Field field = null;
		Method method = null;
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			if ((fields != null) && (fields.length > 0)) {
				for (int i = 0; i < fields.length; i++) {
					field = fields[i];
					Bind id = (Bind) field.getAnnotation(Bind.class);
					if (id != null) {
						boolean setAccessible = false;
						if (!field.isAccessible()) {
							field.setAccessible(true);
							setAccessible = true;
						}
						if (rootView != null) {
							View view = rootView.findViewById(id.value());
							field.set(obj, view);
						} else {
							View view = ((Activity) obj).findViewById(id
									.value());
							field.set(obj, view);
						}
						if (setAccessible) {
							field.setAccessible(false);
						}
					}
				}
			}

			Method[] methods = obj.getClass().getDeclaredMethods();
			if ((methods != null) && (methods.length > 0)) {
				for (int i = 0; i < methods.length; i++) {
					method = methods[i];
					doBind(obj, rootView, method);
					doOnClick(obj, rootView, method);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (DEBUG) {
				android.util.Log.w(TAG, "ButterKnife bind has exception " + e);
				android.util.Log.w(TAG, "ButterKnife bind field: " + field);
				android.util.Log.w(TAG, "ButterKnife bind method: " + method);
			}
		}
	}

	private static void doBind(Object obj, View rootView, Method method) {
		try {
			Bind idForBind = (Bind) method.getAnnotation(Bind.class);
			if (idForBind != null) {
				View view = ((Activity) obj).findViewById(idForBind.value());
				if (view != null) {
					final Object tempObj = obj;
					final Method tempMethod = method;

					Class<?> listenerType = idForBind.type();
					String listenerSetter = "set"
							+ listenerType.getSimpleName();

					Object listener = Proxy.newProxyInstance(
							listenerType.getClassLoader(),
							new Class[] { listenerType },
							new InvocationHandler() {
								@Override
								public Object invoke(Object proxy,
										Method method, Object[] args)
										throws Throwable {
									boolean setAccessible = false;
									if (!tempMethod.isAccessible()) {
										setAccessible = true;
										tempMethod.setAccessible(true);
									}
									Object result = tempMethod.invoke(tempObj,
											args);
									if (setAccessible) {
										tempMethod.setAccessible(false);
									}
									return result;
								}
							});

					Method setEventListenerMethod = view.getClass().getMethod(
							listenerSetter, new Class[] { listenerType });
					setEventListenerMethod.invoke(view,
							new Object[] { listener });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (DEBUG)
				android.util.Log
						.w(TAG, "ButterKnife doBind has exception " + e);
		}
	}

	private static void doOnClick(Object obj, View rootView, Method method) {
		try {
			OnClick idForClick = (OnClick) method.getAnnotation(OnClick.class);
			if (idForClick != null) {
				View view = ((Activity) obj).findViewById(idForClick.value());
				if (view != null) {
					final Object tempObj = obj;
					final Method tempMethod = method;

					Class<?> listenerType = idForClick.type();
					String listenerSetter = "set"
							+ listenerType.getSimpleName();

					Object listener = Proxy.newProxyInstance(
							listenerType.getClassLoader(),
							new Class[] { listenerType },
							new InvocationHandler() {
								@Override
								public Object invoke(Object proxy,
										Method method, Object[] args)
										throws Throwable {
									boolean setAccessible = false;
									if (!tempMethod.isAccessible()) {
										setAccessible = true;
										tempMethod.setAccessible(true);
									}
									Object result = tempMethod.invoke(tempObj,
											args);
									if (setAccessible) {
										tempMethod.setAccessible(false);
									}
									return result;
								}
							});

					Method setEventListenerMethod = view.getClass().getMethod(
							listenerSetter, new Class[] { listenerType });
					setEventListenerMethod.invoke(view,
							new Object[] { listener });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (DEBUG)
				android.util.Log.w(TAG, "ButterKnife doOnClick has exception "
						+ e);
		}
	}

	public static <T extends View> T findById(Activity activity, int id) {
		return (T) activity.findViewById(id);
	}

	public static <T extends View> T findById(View view, int id) {
		return (T) view.findViewById(id);
	}

}
