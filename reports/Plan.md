План тестирования мобильного приложения.
===

## Типы тестирования

* Функциональное
* Дымовое тестирование
* Нефункциональное:
* * Безопасности
* * Конфигурационное
* * Доступности
* * Юзабилити тесты

## Тестовая документация:

* Действующие данные для взаимодействия с приложением в качестве клиента: "Логин" слово "login2"; "Пароль" слово "password2";
* <a href="https://github.com/Gameunkulus/QA-Diplom/blob/main/Check.xls"> Чек-лист </a>;
* <a href="https://github.com/Gameunkulus/QA-Diplom/blob/main/Check.xls"> Список Тест-кейсов </a>;

## Описание структуры приложения:

<a href="https://github.com/Gameunkulus/QA-Diplom/blob/main/AppDescription.xls"> Список элементов </a>;

## Список автоматизированного проверяемого функционала.

* Вход в приложение
* Создание новости
* Редактирование новости
* Удаление новости
* Использование фильтра списка новостей
* Переход между страницами приложения.
* Проверка взаимодействия с тачскрином.
* Проверка совместимости приложения с настройками устройства на котором оно установлено.
* Запуск приложения.

## Перечень используемых инструментов.

| ID | Наименование инструмента | Причина выбора                                                                                                                             |
|:--:|--------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| 1  | Android studio           | Удобная проверенная среда разработки с возможностью добавленя плагинов. Хорошо подходит для работы на языке Java.                          |
| 2  | Java                     | Типизированный объектно-ориентированный язык программирования общего назначения.                                                           |
| 4  | Allure                   | Инструмент для построения понятных отчётов автотестов.                                                                                     |
| 5  | GitHub                   | Самый широко применяемый сервис для IT-проектов и их совместной разработки.                                                                |
| 6  | Charles                  | Инструмент для мониторинга HTTP/HTTPS трафика.                                                                                             | 
| 7  | UI Automator             | Предоставляет удобный способ для анализа разметки и просмотра свойств у её элементов.                                                      | 
| 8  | Espresso                 | Нативный фреймворк для тестирования от Google. Входит в репозиторий Android Support, что позволяет быстро и легко подключить его к проекту | 


## Перечень возможных рисков выполнения сценария.

* Изменения в структуре в базе данных при добавлении в новой информации в эту базу.
* Наличие бага в автотесте, затрудняющего проверку для других автотестов.
* Нарушение в установке и запуске эмулятора в Android Studio.
* Технические неполадки при использовании различных девайсов.

## Список устройств на которых проводится тестирование приложения.

* Properties
  avd.ini.displayname              Pixel 6 Pro API 33
  avd.ini.encoding                 UTF-8
  AvdId                            Pixel_6_Pro_API_33
  disk.dataPartition.size          6442450944
  fastboot.chosenSnapshotFile      
  fastboot.forceChosenSnapshotBoot no
  fastboot.forceColdBoot           no
  fastboot.forceFastBoot           yes
  hw.accelerometer                 yes
  hw.arc                           false
  hw.audioInput                    yes
  hw.battery                       yes
  hw.camera.back                   virtualscene
  hw.camera.front                  emulated
  hw.cpu.ncore                     2
  hw.device.hash2                  MD5:a8abfd3536f3d35e4ba2041a7b99f40e
  hw.device.manufacturer           Google
  hw.device.name                   pixel_6_pro
  hw.dPad                          no
  hw.gps                           yes
  hw.gpu.enabled                   yes
  hw.gpu.mode                      auto
  hw.initialOrientation            Portrait
  hw.keyboard                      yes
  hw.lcd.density                   560
  hw.lcd.height                    3120
  hw.lcd.width                     1440
  hw.mainKeys                      no
  hw.ramSize                       4096
  hw.sdCard                        yes
  hw.sensors.orientation           yes
  hw.sensors.proximity             yes
  hw.trackBall                     no
  image.androidVersion.api         33
  image.sysdir.1                   system-images\android-33\google_apis\x86_64\
  PlayStore.enabled                false
  runtime.network.latency          none
  runtime.network.speed            full
  showDeviceFrame                  yes
  skin.dynamic                     yes
  tag.display                      Google APIs
  tag.id                           google_apis
  vm.heapSize                      384

* Properties
  avd.ini.displayname              Pixel C API 33
  avd.ini.encoding                 UTF-8
  AvdId                            Pixel_C_API_33
  disk.dataPartition.size          6442450944
  fastboot.chosenSnapshotFile      
  fastboot.forceChosenSnapshotBoot no
  fastboot.forceColdBoot           no
  fastboot.forceFastBoot           yes
  hw.accelerometer                 yes
  hw.arc                           false
  hw.audioInput                    yes
  hw.battery                       yes
  hw.camera.back                   virtualscene
  hw.camera.front                  emulated
  hw.cpu.ncore                     2
  hw.device.hash2                  MD5:b6f369a5174fab4bbf46015b0d842ec6
  hw.device.manufacturer           Google
  hw.device.name                   pixel_c
  hw.dPad                          no
  hw.gps                           no
  hw.gpu.enabled                   yes
  hw.gpu.mode                      auto
  hw.initialOrientation            landscape
  hw.keyboard                      yes
  hw.lcd.density                   320
  hw.lcd.height                    1800
  hw.lcd.width                     2560
  hw.mainKeys                      no
  hw.ramSize                       4096
  hw.sdCard                        yes
  hw.sensors.orientation           yes
  hw.sensors.proximity             yes
  hw.trackBall                     no
  image.androidVersion.api         33
  image.sysdir.1                   system-images\android-33\google_apis\x86_64\
  PlayStore.enabled                false
  runtime.network.latency          none
  runtime.network.speed            full
  showDeviceFrame                  yes
  skin.dynamic                     yes
  tag.display                      Google APIs
  tag.id                           google_apis
  vm.heapSize                      192

## Интервальная оценка с учётом рисков в часах.
   
   Приблизительно 72 - 120

## Результаты тестирования.

* Архив:

