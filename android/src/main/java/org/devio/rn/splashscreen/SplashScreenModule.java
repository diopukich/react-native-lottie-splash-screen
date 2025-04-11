package org.devio.rn.splashscreen;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;  // Добавить для New Architecture

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
@ReactModule(name = SplashScreenModule.NAME)  // Добавлено для New Architecture
public class SplashScreenModule extends ReactContextBaseJavaModule {
  public static final String NAME = "SplashScreen";  // Добавлено для New Architecture
  public SplashScreenModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return NAME;  // Изменено для соответствия константе
  }

  /**
   * Закрыть сплеш-скрин
   */
  @ReactMethod
  public void hide() {
    SplashScreen.hide(getCurrentActivity());
  }
}
