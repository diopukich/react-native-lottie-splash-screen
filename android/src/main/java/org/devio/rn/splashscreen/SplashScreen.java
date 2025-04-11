package org.devio.rn.splashscreen;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.WindowManager;
import com.airbnb.lottie.LottieAnimationView;
import java.lang.ref.WeakReference;

/**
 * SplashScreen
 */
public class SplashScreen {
  private static Dialog mSplashDialog;
  private static WeakReference<Activity> mActivity;
  private static Boolean isAnimationFinished = false;
  private static Boolean waiting = false;

  /**
   * Показать сплеш-скрин
   */
  public static void show(final Activity activity, final int themeResId, final int lottieId) {
    if (activity == null)
      return;
    mActivity = new WeakReference<Activity>(activity);
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (!activity.isFinishing()) {
          mSplashDialog = new Dialog(activity, themeResId);
          mSplashDialog.setContentView(R.layout.launch_screen);
          mSplashDialog.setCancelable(false);
          setActivityAndroidP(mSplashDialog);
          LottieAnimationView lottie = (LottieAnimationView) mSplashDialog.findViewById(lottieId);

          lottie.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
              System.out.println("asdf");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
              SplashScreen.setAnimationFinished(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
          });

          if (!mSplashDialog.isShowing()) {
            mSplashDialog.show();
          }
        }
      }
    });
  }

  public static void setAnimationFinished(boolean flag) {
    if (mActivity == null) {
      return;
    }

    isAnimationFinished = flag;

    final Activity _activity = mActivity.get();
    if (_activity == null) {
      return; // Добавлена дополнительная проверка на null
    }

    _activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (mSplashDialog != null && mSplashDialog.isShowing()) {
          boolean isDestroyed = false;

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isDestroyed = _activity.isDestroyed();
          }

          // Проверка убрана, вместо неё просто ждем завершения анимации
          if (!_activity.isFinishing() && !isDestroyed && waiting) {
            mSplashDialog.dismiss();
            mSplashDialog = null;
          }
        }
      }
    });
  }

  private static void setActivityAndroidP(Dialog dialog) {
    if (Build.VERSION.SDK_INT >= 28) {
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            dialog.getWindow().setAttributes(lp);
        }
    }
  }

  public static void show(final Activity activity, int lottieId) {
    int resourceId = R.style.SplashScreen_Fullscreen; 
    show(activity, resourceId, lottieId);
  }

  /**
   * Скрыть сплеш-скрин
   */
  public static void hide(Activity activity) {
    if (activity == null) {
      if (mActivity == null) {
        return;
      }
      activity = mActivity.get();
    }

    if (activity == null)
      return;

    waiting = true;

    final Activity _activity = activity;

    _activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (mSplashDialog != null && mSplashDialog.isShowing()) {
          boolean isDestroyed = false;

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isDestroyed = _activity.isDestroyed();
          }

          // Проверка убрана, вместо неё просто ждем завершения анимации
          if (!_activity.isFinishing() && !isDestroyed && isAnimationFinished) {
            mSplashDialog.dismiss();
            mSplashDialog = null;
          }
        }
      }
    });
  }
}