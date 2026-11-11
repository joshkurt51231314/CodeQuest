CODE QUEST (XML VERSION)
========================

This is the same app as the Jetpack Compose version, rebuilt with XML layouts so you can
drag and resize elements in Android Studio's Design tab.

SETUP (do these in order)
1. Keep your Compose project as a backup. Do NOT delete it.
2. Extract this zip to a simple folder, for example C:\Projects\CodeQuestXML
3. Copy your font file press_start_2p.ttf (from the old project: app\src\main\res\font\)
   into this project at:  app\src\main\res\font\press_start_2p.ttf
   (create the "font" folder if it does not exist; the file name must have ONE .ttf)
4. In Android Studio: File > Open, choose the folder that directly contains
   settings.gradle.kts and the "app" folder.
5. If asked about the Gradle JVM, choose JVM 21. Let Gradle sync (needs internet).
6. Press Run.

EDITING THE DESIGN
Open app\src\main\res\layout\ and double-click a file, then choose the Design tab:
  activity_title.xml   Title screen     (drag-friendly)
  activity_login.xml   Login screen     (drag-friendly)
  activity_signup.xml  Sign up screen   (drag-friendly)
  activity_quiz.xml    Quiz screen      (drag-friendly)
  activity_result.xml  Results screen   (simple vertical layout: move items by reordering)

Colors:  res\values\colors.xml
Fonts/text styles/buttons:  res\values\styles.xml
Box shapes, shadows, background:  res\drawable\

WHERE THE CODE IS
  java\...\ui\       one Activity per screen
  java\...\QuizViewModel.kt   game rules + 10 second timer
  java\...\data\     questions, Room database, login/score repositories
