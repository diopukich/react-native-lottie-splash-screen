//  Dynamic.swift
//  Updated for lottie-ios 4.5.0

import UIKit
import Foundation
import Lottie

@objc class Dynamic: NSObject {

  @objc func createAnimationView(rootView: UIView, lottieName: String) -> LottieAnimationView {
    // Создаем анимацию с использованием актуального API
    let animationView = LottieAnimationView(name: lottieName)
    animationView.frame = rootView.frame
    animationView.center = rootView.center
    animationView.backgroundColor = UIColor.white
    // Правильные настройки для версии 4.5.0
    animationView.contentMode = .scaleAspectFit
    // Дополнительные улучшения
    animationView.loopMode = .playOnce
    return animationView
  }

  @objc func play(animationView: LottieAnimationView) {
    // Воспроизведение с использованием актуального API
    animationView.play { completed in
      RNSplashScreen.setAnimationFinished(true)
    }
  }
}