package de.eqee.pn.ui;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;

import de.eqee.pn.R;
import de.eqee.pn.databinding.ActivityShowGiphyBinding;
import de.eqee.pn.ui.util.GiphyUIHelper;
import de.eqee.pn.ui.util.UriHelper;

public class ShowGiphyActivity extends GiphyActivity {

	private ActivityShowGiphyBinding binding;

	private Uri createGiphyUri() {
		return Uri.parse("giphy:" + this.giphyID);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.binding = DataBindingUtil.setContentView(this,R.layout.activity_show_giphy);
		setSupportActionBar((Toolbar) binding.toolbar);

		configureActionBar(getSupportActionBar());

		final Intent intent = getIntent();

		if (intent != null) {
			final String action = intent.getAction();
			if (action == null) {
				return;
			}
			switch (action) {
				case "de.eqee.pn.giphy.show":
					if (intent.hasExtra("giphy")) {
						final String gphid = intent.getStringExtra("giphy");
						this.giphyID = gphid;
					}
					break;
				case Intent.ACTION_VIEW:
					final Uri giphyUri = intent.getData();

					if (giphyUri != null) {
						final HashMap<String, String> query = UriHelper.parseQueryString(giphyUri.getQuery());

						final String schemeSpecificPart = giphyUri.getSchemeSpecificPart();

						if (schemeSpecificPart != null && !schemeSpecificPart.isEmpty()) {
							try {
							} catch (final NumberFormatException ignored) {
							}
						}
					}

					break;
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(final int requestCode,
										   @NonNull final String[] permissions,
										   @NonNull final int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		updateUi();
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.menu_show_giphy, menu);
		updateUi();
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

    @Override
    protected void updateUi() {

    }

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_copy_giphy:
				final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				if (clipboard != null) {
					final ClipData clip = ClipData.newPlainText("giphy", createGiphyUri().toString());
					clipboard.setPrimaryClip(clip);
					Toast.makeText(this,R.string.url_copied_to_clipboard,Toast.LENGTH_SHORT).show();
				}
				return true;
			case R.id.action_share_giphy:
				final Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.putExtra(Intent.EXTRA_TEXT, createGiphyUri().toString());
				shareIntent.setType("text/plain");
				try {
					startActivity(Intent.createChooser(shareIntent, getText(R.string.share_with)));
				} catch (final ActivityNotFoundException e) {
					//This should happen only on faulty androids because normally chooser is always available
					Toast.makeText(this, R.string.no_application_found_to_open_file, Toast.LENGTH_SHORT).show();
				}
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
