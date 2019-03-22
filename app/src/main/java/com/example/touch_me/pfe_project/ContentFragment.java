package com.example.touch_me.pfe_project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

public class ContentFragment extends Fragment implements ScreenShotable {
  public static final String CLOSE = "Close";
  public static final String CHART = "Chart";
  public static final String BOOK = "Book";
  public static final String PAINT = "Paint";
  public static final String CASE = "Case";
  public static final String SHOP = "Shop";
  public static final String PARTY = "Party";
  public static final String MOVIE = "Movie";

  private View containerView;
  protected ImageView mImageView;
  protected int res;
  private Bitmap bitmap;

  public static ContentFragment newInstance(int resId) {
    ContentFragment contentFragment = new ContentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(Integer.class.getName(), resId);
    contentFragment.setArguments(bundle);
    return contentFragment;
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.containerView = view.findViewById(R.id.container);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    res = getArguments().getInt(Integer.class.getName());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    return rootView;
  }

  @Override
  public void takeScreenShot() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
          containerView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        containerView.draw(canvas);
        ContentFragment.this.bitmap = bitmap;
      }
    };

    thread.start();

  }

  @Override
  public Bitmap getBitmap() {
    return bitmap;
  }
}
