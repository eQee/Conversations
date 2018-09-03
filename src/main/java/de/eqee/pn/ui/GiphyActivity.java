package de.eqee.pn.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;

import org.osmdroid.config.Configuration;
import org.osmdroid.config.IConfigurationProvider;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.views.MapView;

import java.io.File;
import java.io.IOException;

import de.eqee.pn.BuildConfig;
import de.eqee.pn.Config;
import de.eqee.pn.R;
import de.eqee.pn.http.HttpConnectionManager;
import de.eqee.pn.ui.util.GiphyUIHelper;
import de.eqee.pn.utils.ThemeHelper;

public abstract class GiphyActivity extends ActionBarActivity {
	public static final int REQUEST_CODE_CREATE = 0;
	public static final int REQUEST_CODE_FAB_PRESSED = 1;
	public static final int REQUEST_CODE_SNACKBAR_PRESSED = 2;

	public String giphyID = "";

	private MapView map = null;
	private GiphyUIHelper GHelper = new GiphyUIHelper();

	protected int mTheme;

	protected XYTileSource tileSource() {
		return new XYTileSource("OpenStreetMap",
				0, 19, 256, ".png", new String[] {
				"https://a.tile.openstreetmap.org/",
				"https://b.tile.openstreetmap.org/",
				"https://c.tile.openstreetmap.org/" },"© OpenStreetMap contributors");
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context ctx = getApplicationContext();
		this.mTheme = ThemeHelper.find(this);
		setTheme(this.mTheme);

		final IConfigurationProvider config = Configuration.getInstance();
		config.load(ctx, getPreferences());
		config.setUserAgentValue(BuildConfig.APPLICATION_ID + "_" + BuildConfig.VERSION_CODE);
		if (Config.FORCE_ORBOT || getBooleanPreference("use_tor", R.bool.use_tor)) {
			try {
				config.setHttpProxy(HttpConnectionManager.getProxy());
			} catch (IOException e) {
				throw new RuntimeException("Unable to configure proxy");
			}
		}

		final File f = new File(ctx.getCacheDir() + "/tiles");
		try {
			//noinspection ResultOfMethodCallIgnored
			f.mkdirs();
		} catch (final SecurityException ignored) {
		}
		if (f.exists() && f.isDirectory() && f.canRead() && f.canWrite()) {
			Log.d(Config.LOGTAG, "Using tile cache at: " + f.getAbsolutePath());
			config.setOsmdroidTileCache(f.getAbsoluteFile());
		}
	}

	@Override
	protected void onSaveInstanceState(@NonNull final Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Configuration.getInstance().save(this, getPreferences());
		map.onPause();
		try {
		} catch (final SecurityException ignored) {
		}
	}

	protected abstract void updateUi();

	protected boolean mapAtInitialLoc() {
		return map.getZoomLevelDouble() == Config.Map.INITIAL_ZOOM_LEVEL;
	}

	@Override
	protected void onResume() {
		super.onResume();
		Configuration.getInstance().load(this, getPreferences());
		map.onResume();

		updateUi();
		map.setTileSource(tileSource());
		map.setTilesScaledToDpi(true);
	}

	protected SharedPreferences getPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	}

	protected boolean getBooleanPreference(String name, @BoolRes int res) {
		return getPreferences().getBoolean(name, getResources().getBoolean(res));
	}

    protected boolean isGiphyEnabled() {
        try {
            final int locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } catch( final Settings.SettingNotFoundException e ){
            return false;
        }
    }
}
