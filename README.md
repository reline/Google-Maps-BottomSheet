# Pilof
A BottomSheetBehavior framework mirroring Google Maps

## Usage

Initializing and modifying a SheetBehavior is the same as the BottomSheetBehavior from the support library
```java
View bottomsheet = findViewById(R.id.bottomsheet);
SheetBehavior behavior = SheetBehavior.from(bottomsheet);
behavior.setPeekHeight(200); // sets peek height in pixels
behavior.setState(SheetBehavior.STATE_COLLAPSED);
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
    app:layout_behavior="xyz.projectplay.pilof.SheetBehavior">
    ...
</android.support.v4.widget.NestedScrollView>
```

### Framework features

##### Anchoring
```java
behavior.setAnchor(200); // sets anchor offset from the top of the screen in pixels
```
```
app:behavior_anchorOffset="200dp"
```

##### Parallax
```java
View parallax = findViewById(R.id.parallax);
behavior.setParallax(parallax);
```
```xml
<View
    android:id="@+id/parallax"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:background="@color/colorPrimaryDark"
    android:visibility="gone"/>
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
    app:behavior_header_layout="@layout/custom_header"
    app:behavior_content_layout="@layout/custom_content"
    app:behavior_anchorColor="@color/colorPrimary"
    app:behavior_collapsedColor="@android:color/white"
    app:layout_behavior="xyz.projectplay.pilof.SheetBehavior" />
```
