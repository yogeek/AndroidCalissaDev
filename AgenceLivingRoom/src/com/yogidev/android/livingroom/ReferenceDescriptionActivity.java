package com.yogidev.android.livingroom;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yogidev.android.livingroom.data.bean.Reference;

public class ReferenceDescriptionActivity extends FragmentActivity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
	 * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	
	/**
	 * The {@link ViewPager} that will display the three primary sections of the app, one at a
	 * time.
	 */
	ViewPager mViewPager;
	
	// The bundle to pass and receive data to and from other activities
	Bundle objetbunble;
	
	Reference currentReference;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    // Restore pref theme
	    setTheme(PreferencesManager.getInstance().getThemePref());
		
	    // Get the Bundle sent by the previous activity
	    objetbunble  = getIntent().getExtras();
		
	    // Create the bundle if null
	    if (objetbunble == null) {
	    	objetbunble = new Bundle();
	    }
	    
	    // Get the currentReference in bundle
	    currentReference = getIntent().getParcelableExtra("currentReference");

	    // Inflate the view from XML
		setContentView(R.layout.reference_view_pager);
		
		// set transparency 
		getWindow().getDecorView().getRootView().setAlpha(PreferencesManager.TRANPARENCY);

		// Create the adapter that will return a fragment for each of the three primary sections
		// of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager(), this, currentReference);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home/Up button should not be enabled, since there is no hierarchical
		// parent.
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);

		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager, attaching the adapter and setting up a listener for when the
		// user swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.referenceViewPager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// When swiping between different app sections, select the corresponding tab.
				// We can also use ActionBar.Tab#select() to do this if we have a reference to the
				// Tab.
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by the adapter.
			// Also specify this Activity object, which implements the TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(
					actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	
	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}
	
    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
        	mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }
   
    
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
	 * sections of the app.
	 */
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {
		Context context;
		final int PAGE_COUNT = 3;
		Reference mRef;

		public AppSectionsPagerAdapter(FragmentManager fm, Context nContext, Reference ref) {
			super(fm);
			context = nContext;
			mRef = ref;
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = null;
			switch (i) {
			case 0:
				// The first section of the app : "Description" and path to the pictures collection gallery
				fragment = new DescriptionSectionFragment();
				Bundle argsDesc = new Bundle();
				// Good practice for passing parceable object to fragment
				argsDesc.putParcelable(DescriptionSectionFragment.ARG_CURRENT_REF, mRef);
				fragment.setArguments(argsDesc);
				break;
			case 1:
				// The second section of the app : "Details"
				fragment = new DetailsSectionFragment();
				Bundle argsDetails = new Bundle();
				argsDetails.putInt(DetailsSectionFragment.ARG_DETAILS_NUMBER, i + 1);
				argsDetails.putParcelable(DescriptionSectionFragment.ARG_CURRENT_REF, mRef);
                fragment.setArguments(argsDetails);
                break;
			case 2:
				// The third section of the app : "Carte"
				fragment = new CarteSectionFragment();
				Bundle argsCarte = new Bundle();
				argsCarte.putInt(CarteSectionFragment.ARG_CARTE_NUMBER, i + 1);
				argsCarte.putParcelable(DescriptionSectionFragment.ARG_CURRENT_REF, mRef);
                fragment.setArguments(argsCarte);
                break;
			default:
				// never called
				fragment = new Fragment();
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return PAGE_COUNT;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String pageTitle = "Titre manquant";
			switch (position) {
			case 0:
				pageTitle =  context.getString(R.string.descriptionReferenceTitre);
				break;
			case 1:
				pageTitle = context.getString(R.string.detailsReferenceTitre);
				break;
			case 2:
				pageTitle = context.getString(R.string.carteReferenceTitre);
				break;
			default:
				break;
			}
			return pageTitle;
		}
	}

	/**
	 * A fragment that shows the description of the reference :
	 * - the title
	 * - the first picture which displays the gallery when clicked
	 * - general information
	 */
	public static class DescriptionSectionFragment extends Fragment {
		
		public static final String ARG_CURRENT_REF= "mCurrentRef";
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_description_reference, container, false);
			
			Bundle args = getArguments();
			Reference ref = args.getParcelable(ARG_CURRENT_REF);
			
			// Fill the Title
			TextView titreView = (TextView) rootView.findViewById(R.id.textTitreRef);
			titreView.setText(ref.getVille() + " - " + ref.getQuartier());
			
			// Fill the main image 
			ImageButton collectionButton = (ImageButton) rootView.findViewById(R.id.demo_collection_button);
//			new DownloadImageTask(collectionButton,getResources().getDrawable(R.drawable.logo)).execute(ref.FirstPhoto());
			new DownloadImageTask(collectionButton,getResources().getDrawable(R.drawable.logo)).execute(CollectionGalleryActivity.photosList.get(0));
			
			// Fill the Description
			TextView descView = (TextView) rootView.findViewById(R.id.textDescRef);
			String details = "";
			String charges = "Charges";
			if (ref.isLocation()) {
				details += "Loyer hors charges:" + ref.getLoyerOuPrix() + "€\n";
				charges += " copropriété:";  
			}
			details += charges + ref.getChargesOuCopro() + "€" + (ref.isLocation()?"/an":""); 
			
					
			descView.setText(details);

			// Demonstration of a collection-browsing activity.
			collectionButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(getActivity(), CollectionGalleryActivity.class);
					intent.putExtras(getActivity().getIntent().getExtras());
					startActivity(intent);
				}
			});

			// Demonstration of navigating to external activities.
			rootView.findViewById(R.id.demo_external_activity)
			.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					// Create an intent that asks the user to pick a photo, but using
					// FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
					// the application from the device home screen does not return
					// to the external activity.
					Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
					externalActivityIntent.setType("image/*");
					externalActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
					startActivity(externalActivityIntent);
				}
			});

			return rootView;
		}
	}

	/**
	 * A fragment representing the "details" section of the app.
	 */
	public static class DetailsSectionFragment extends Fragment {

		public static final String ARG_DETAILS_NUMBER = "details_number";
		public static final String ARG_CURRENT_REF= "mCurrentRef";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_details_reference, container, false);
			Bundle args = getArguments();

			Reference ref = args.getParcelable(ARG_CURRENT_REF);
			
			// Fill the Description
			TextView descView = (TextView) rootView.findViewById(R.id.textDetailsRef);
			descView.setText(ref.getDescriptif());
			
			// Fill the Equipment
			if (ref.getListeEquipements() != null && ref.getListeEquipements().size() != 0) {
				TextView eqtView = (TextView) rootView.findViewById(R.id.textEquipement);
				eqtView.setText(eqtView.getText() + "\n" + ref.getListeEquipements().get(0));
			}
			
			
			return rootView;
		}
	}
	
	/**
	 *  A fragment representing the "Carte" section of the app.
	 */
	public static class CarteSectionFragment extends Fragment {

		public static final String ARG_CARTE_NUMBER = "carte_number";
		public static final String ARG_CURRENT_REF= "mCurrentRef";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_carte_reference, container, false);
			Bundle args = getArguments();
			((TextView) rootView.findViewById(android.R.id.text1)).setText(getString(R.string.dummy_section_text,args.getInt(ARG_CARTE_NUMBER)));
			return rootView;
		}
	}
}

