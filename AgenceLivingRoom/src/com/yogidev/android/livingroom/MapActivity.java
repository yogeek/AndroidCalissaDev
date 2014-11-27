package com.yogidev.android.livingroom;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yogidev.android.livingroom.data.bean.Reference;
import com.yogidev.android.livingroom.data.mock.MockReferenceList;

public class MapActivity extends Activity {
	
	private GoogleMap map;
	
	static final LatLng AGENCE = new LatLng(43.613888, 1.442122);
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.map_reference);
	    
	    // get the map fragment
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	    
	    // show my location
	    map.setMyLocationEnabled(true);
	    // show the "locate me" button
	    map.getUiSettings().setMyLocationButtonEnabled(true);
	    
	    // fill the map
	    
	    for (Reference ref : MockReferenceList.REFERENCE_LIST) {
	    
	    	// Reference marker
	    	map.addMarker(new MarkerOptions()
	    	.position(ref.getLatLng())
	    	.title(ref.getTitreRef())
	    	.snippet(ref.getVille() + ((ref.getQuartier() == null && ref.getQuartier().isEmpty())?"":" - " + ref.getQuartier()))
	    	.icon(BitmapDescriptorFactory
	    			.fromResource(ref.getMapMarkerDrawable())));
	    	
	    }
	    
	    // AGENCE marker
	    Marker agence = map.addMarker(new MarkerOptions()
        .position(AGENCE)
        .title("LivingRoom")
        .snippet("Caroline travaille ici !")
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
	    
	    agence.showInfoWindow();
	    
	    map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				Toast.makeText(getApplicationContext(), "Reference = " + marker.getTitle() + "\n[" +  marker.getPosition() + "]", Toast.LENGTH_SHORT).show();
			}
		});

	    // Move the camera instantly to hamburg with a zoom of 15.
	    map.moveCamera(CameraUpdateFactory.newLatLngZoom(AGENCE, 5));

	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
	    
	    
	    // Get the Action Bar 
	    ActionBar actionBar = getActionBar();
	    // Enable the app icon as an Up button
	    actionBar.setDisplayHomeAsUpEnabled(true);
	    // Set title
	    actionBar.setTitle("Sur la carte");
	
	}
	
}
