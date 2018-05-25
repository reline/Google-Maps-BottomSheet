# Google Maps BottomSheet Behavior
A BottomSheetBehavior framework mirroring Google Maps

## Demo

![Demo](https://raw.githubusercontent.com/Reline/GoogleMapsBottomSheet/master/extras/demo.gif)

## Usage

Initializing and modifying a GoogleMapsBottomSheetBehavior is the same as the BottomSheetBehavior from the support library
```java
View bottomsheet = findViewById(R.id.bottomsheet);
GoogleMapsBottomSheetBehavior behavior = GoogleMapsBottomSheetBehavior.from(bottomsheet);
behavior.setPeekHeight(200); // in pixels
// or use the default peek height, which is different from the support library
behavior.setPeekHeight(PEEK_HEIGHT_AUTO);
behavior.setState(GoogleMapsBottomSheetBehavior.STATE_COLLAPSED);
behavior.setHideable(false);
```
```xml
<android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/bottomsheet"
    android:background="@android:color/white"
    android:layout_gravity="bottom"
    app:behavior_hideable="true"
    app:behavior_peekHeight="100dp"
    app:layout_behavior="com.github.reline.GoogleMapsBottomSheetBehavior">
    ...
</android.support.v4.widget.NestedScrollView>
```

### Framework features

##### Anchoring
Using the offset from the top of the parent view
```java
behavior.setAnchorOffset(200); // in pixels
```
or set the actual height of the bottomsheet when it is anchored
```xml
app:behavior_anchorHeight="200dp"
<!-- or use the default 16:9 ratio keyline -->
app:behavior_anchorHeight="auto"
```
```java
behavior.setAnchorHeight(1100); // in pixels
// or use the default 16:9 ratio keyline
behavior.setAnchorHeight(ANCHOR_HEIGHT_AUTO);
```

##### Parallax
```java
View parallax = ...;
behavior.setParallax(parallax);
```
```xml
<View
    android:id="@+id/parallax"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:background="@color/colorPrimaryDark"/>
```

TIP: Use `behavior.getAnchorOffset()` to make the parallax fill the entire gap between the anchor and the top of the screen.
```
LayoutParams layoutParams = new LayoutParams(parallax.getMeasuredWidth(), behavior.getAnchorOffset());
parallax.setLayoutParams(layoutParams);
```

##### Custom Header/Content and Color Animations
Color animations can easily be handled by the framework if given a header and content view.

Be advised, when using a custom heading/content view a child should not be provided to the bottom sheet.

```xml
<android.support.v4.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/bottomsheet"
    android:background="@android:color/white"
    android:layout_gravity="bottom"
    app:behavior_hideable="true"
    app:behavior_peekHeight="100dp"
    app:behavior_anchorHeight="auto"
    app:behavior_header_layout="@layout/custom_header"
    app:behavior_content_layout="@layout/custom_content"
    app:behavior_anchorColor="@color/colorPrimary"
    app:behavior_collapsedColor="@android:color/white"
    app:layout_behavior="com.github.reline.GoogleMapsBottomSheetBehavior" />
```

### Gradle

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.reline:google-maps-bottomsheet:1.0'
}
```
