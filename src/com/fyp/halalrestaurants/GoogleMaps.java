package com.fyp.halalrestaurants;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMaps extends FragmentActivity {

	// static final LatLng KIEL = new LatLng(53.551, 9.993);
	// private static final LatLng LatLng =new LatLng(53.558, 9.927);
	private GoogleMap map;
	private String latitude;
	private String longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.google_maps);
		Bundle b = this.getIntent().getExtras();
		String name = b.getString("res_name");
		String address = b.getString("res_address");

		latitude = b.getString("lat");
		longitude = b.getString("lng");

		double lat = Double.parseDouble(latitude);
		double lng = Double.parseDouble(longitude);

		// latitude=b.getFloat("latitude");
		// longitude=b.getFloat("longitude");

		LatLng l_latlng = new LatLng(lat, lng);

		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		// Getting the user current location
		map.setMyLocationEnabled(true);
		map.getUiSettings().setMyLocationButtonEnabled(true);

		// Setting map type
		// map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		// map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		// map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

		if (map != null) {
			Marker agentLocation = map.addMarker(new MarkerOptions()
					.position(l_latlng)

					.title(name)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.bubble)));

			// Move the camera
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(l_latlng, 8));

			map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

			// map.addMarker(new MarkerOptions().position(HAMBURG)
			// .title("Hamburg"));
			// map.addMarker(new MarkerOptions()
			// .position(KIEL)
			// .title("Kiel")
			// .snippet("Kiel is cool")
			// .icon(BitmapDescriptorFactory
			// .fromResource(R.drawable.ic_launcher)));
			// }

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.google_maps, menu);
		return true;

	}
}