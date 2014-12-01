package com.yogidev.android.livingroom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.yogidev.android.livingroom.data.bean.Reference;
import com.yogidev.android.livingroom.data.util.Constants;

/**
 * A sample showing how to zoom an image thumbnail to full-screen, by animating the bounds of the
 * zoomed image from the thumbnail bounds to the screen bounds.
 *
 * <p>In this sample, the user can touch one of two images. Touching an image zooms it in, covering
 * the entire activity content area. Touching the zoomed-in image hides it.</p>
 */
public class ZoomGalleryActivity extends FragmentActivity {
    /**
     * Hold a reference to the current animator, so that it can be canceled mid-way.
     */
    private Animator mCurrentAnimator;

    /**
     * The system "short" animation time duration, in milliseconds. This duration is ideal for
     * subtle animations or animations that occur very frequently.
     */
    private int mShortAnimationDuration;
    
    // The bundle to pass and receive data to and from other activities
 	Bundle objetbunble;
 	
 	Reference currentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Restore pref theme
	    setTheme(PreferencesManager.getInstance().getThemePref());
        
	    // Get the Bundle sent by the previous activity
	    objetbunble  = getIntent().getExtras();
	    
	    // Create the bundle if null
	    if (objetbunble == null) {
	    	objetbunble = new Bundle();
	    }
        
	    currentReference = getIntent().getParcelableExtra(Constants.CURRENT_REFERENCE);
        
        setContentView(R.layout.activity_zoom);
        
        // Set background
	    findViewById(R.id.containerZoom).setBackground(PreferencesManager.getInstance().getBackgoundColorPref());
        
        // Get the GridLayout to fill it with thumbnails
//        GridLayout zoomLinear = (GridLayout)findViewById(R.id.zoomGrid);
//        
// 
//        // Size calculus
//        Point size = new Point();
//        getWindowManager().getDefaultDisplay().getSize(size);
//        int screenWidth = size.x;
//        int tiersScreenWidth = (int)(screenWidth/3);
//        
//        // Construct the grid cells dynamically with the reference photos as TouchHighlightImageButton
//        int photoIndex = 0;
//        while (photoIndex < currentReference.getPhotos().size()) {
//	        for (int colIndex = 0; colIndex < zoomLinear.getColumnCount() && photoIndex < currentReference.getPhotos().size(); colIndex++) {
//	        	
//	        	final String photo = currentReference.getPhotos().get(photoIndex);
//	        	
//	        	// Create a touch highlight button
//	        	final TouchHighlightImageButton btnPhoto = new TouchHighlightImageButton(this);
//	        	btnPhoto.setId(photoIndex);
//	        	
//	        	// layout = row (photoIndex modulo 3 pour avoir 0,1,2;0,1,2...) / col (colIndex)
//	        	int rowInd = photoIndex/3;
//	        	int colInd = colIndex;
////	        	GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(rowInd), GridLayout.spec(colInd));
////	        	GridLayout.LayoutParams params = new GridLayout.LayoutParams(new ViewGroup.MarginLayoutParams(tiersScreenWidth-10,150-10));
////	            params.setMargins(5, 5, 5, 5);
//	        	GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//	            params.setGravity(Gravity.CENTER);
//	        	btnPhoto.setLayoutParams(params);
//	        	
//	        	btnPhoto.setScaleType(ScaleType.CENTER_CROP);
//	        	// fill the Image button with the current photo
//	        	new DownloadImageTask(btnPhoto,getResources().getDrawable(R.drawable.logo)).execute(photo);
//	        	btnPhoto.setOnClickListener(new View.OnClickListener() {
//	                @Override
//	                public void onClick(View view) {
//	                    zoomImageFromThumb(btnPhoto, photo);
//	                }
//	            });
//	        	zoomLinear.addView(btnPhoto);
//	        	photoIndex++;
//	        }
//        }
        
        //--------------------------- Grid View --------------------------------
        
        GridView gridView = (GridView) findViewById(R.id.gridView);
        GridViewAdapter customGridAdapter = new GridViewAdapter(this, R.layout.row_grid, currentReference.getPhotos());
		gridView.setAdapter(customGridAdapter);
        
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				v.setSelected(true);
				Toast.makeText(ZoomGalleryActivity.this, "Titre de la photo " + (position-1) , Toast.LENGTH_SHORT).show();
				zoomImageFromThumb(v, currentReference.getPhotos().get(position));
			}

	});
        
        
        //-----------------------------------------------------------------------
        
        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        
        // Specify that the Home button should show an "Up" caret, indicating that touching the
        // button will take the user one step up in the application's hierarchy.
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                // Create a simple intent that starts the hierarchical parent activity and
                // use NavUtils in the Support Package to ensure proper handling of Up.
                Intent upIntent = new Intent(this, ReferenceDescriptionActivity.class);
                upIntent.putExtras(objetbunble);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is not part of the application's task, so create a new task
                    // with a synthesized back stack.
                    TaskStackBuilder.from(this)
                            // If there are ancestor activities, they should be added here.
                            .addNextIntent(upIntent)
                            .startActivities();
                    finish();
                } else {
                    // This activity is part of the application's task, so simply
                    // navigate up to the hierarchical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * "Zooms" in a thumbnail view by assigning the high resolution image to a hidden "zoomed-in"
     * image view and animating its bounds to fit the entire activity content area. More
     * specifically:
     *
     * <ol>
     *   <li>Assign the high-res image to the hidden "zoomed-in" (expanded) image view.</li>
     *   <li>Calculate the starting and ending bounds for the expanded view.</li>
     *   <li>Animate each of four positioning/sizing properties (X, Y, SCALE_X, SCALE_Y)
     *       simultaneously, from the starting bounds to the ending bounds.</li>
     *   <li>Zoom back out by running the reverse animation on click.</li>
     * </ol>
     *
     * @param thumbView  The thumbnail view to zoom in.
     * @param photoUrl The URL of the photo represented by the thumbnail.
     */
    private void zoomImageFromThumb(final View thumbView, String photoUrl) {
        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        //expandedImageView.setImageResource(imageResId);
        expandedImageView.setImageResource(R.drawable.logo);
        new DownloadImageTask(expandedImageView,getResources().getDrawable(R.drawable.logo)).execute(photoUrl);

        // Calculate the starting and ending bounds for the zoomed-in image. This step
        // involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail, and the
        // final bounds are the global visible rectangle of the container view. Also
        // set the container view's offset as the origin for the bounds, since that's
        // the origin for the positioning animation properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.containerZoom).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
        // "center crop" technique. This prevents undesirable stretching during the animation.
        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation begins,
        // it will position the zoomed-in view in the place of the thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
        // the zoomed-in view (the default is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
        // and show the thumbnail instead of the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel, back to their
                // original values.
                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
