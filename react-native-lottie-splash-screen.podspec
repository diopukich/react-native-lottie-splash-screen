require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "react-native-lottie-splash-screen"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.author       = 'taehyun'
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  # Обновляем минимальную версию iOS до 15.1
  s.platform     = :ios, "15.1"
  s.source       = { :git => "https://github.com/HwangTaehyun/react-native-lottie-splash-screen", :tag => "v#{s.version}" }
  # Включаем все исходные файлы
  s.source_files  = "ios/*.{h,m,swift}"
  # Указываем совместимость с lottie-ios 4.5.0
  s.dependency "lottie-ios", "4.5.0"
  s.dependency "React-Core"
  # Настройки Swift
  s.requires_arc = true
  s.swift_version = '5.0'
end