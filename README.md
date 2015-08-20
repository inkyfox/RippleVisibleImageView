# RippleVisibleImageView
You can show the touch ripple effect on Android ImageView easily. This is basically ImageView enabled the foreground especially for a hover effect, which also supports RippleDrawable from API21. You can also use this for masking an image with a frame image.

### ScreenShot
![sreenshot](https://github.com/inkyfox/RippleVisibleImageView/blob/master/screenshot/RippleVisibleImageView.gif)

### Usage (XML)
Use just `android:foreground` attribute for a drawable to show over ImageView.
```xml
    <com.genxhippies.library.RippleVisibleImageView
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:scaleType="centerCrop"
        android:src="@drawable/newyork"
        android:foreground="@drawable/touch_overlay"
        android:clickable="true" />
```

### Usage (Java)
You can use this with your code of course.
```java
    RippleVisibleImageView imageView = findViewById(R.id.image_view);
    imageView.setForeground(R.drawable.touch_overlay);
```

### Import
Project build.gradle

```
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```

Module build.gradle
```
dependencies {
	   compile 'com.github.inkyfox:RippleVisibleImageView:1.0.0'
}
```

### LICENSE
[LICENSE UNDER MIT](https://github.com/fenjuly/ArrowDownloadButton/raw/master/LICENSE)
