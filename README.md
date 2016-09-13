# Google Maps BottomSheet

## Usage

```xml
<android.support.v4.widget.NestedScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/bottom_sheet"
            android:background="@android:color/white"
            android:layout_gravity="bottom"
            app:behavior_hideable="true"
            app:behavior_peekHeight="100dp"
            app:behavior_anchorOffset="200dp"
            app:layout_behavior="xyz.projectplay.googlemapsbottomsheet.GoogleMapsBottomSheetBehavior">

            ...

</android.support.v4.widget.NestedScrollView>
