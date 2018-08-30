package de.eqee.pn.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import de.eqee.pn.R;
import de.eqee.pn.databinding.ActivityShareGiphyBinding;
import de.eqee.pn.ui.util.GiphyUIHelper;
import de.eqee.pn.utils.ThemeHelper;

public class ShareGiphyActivity extends GiphyActivity {

	private Snackbar snackBar;
	private ActivityShareGiphyBinding binding;
	private Boolean noAskAgain = false;

	@Override
	protected void onSaveInstanceState(@NonNull final Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	@Override
	protected void onRestoreInstanceState(@NonNull final Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.binding = DataBindingUtil.setContentView(this,R.layout.activity_share_giphy);
		setSupportActionBar((Toolbar) binding.toolbar);
		configureActionBar(getSupportActionBar());

		//this.snackBar = Snackbar.make(this.binding.snackbarCoordinator, R.string.giphy_disabled, Snackbar.LENGTH_INDEFINITE);
		this.snackBar.setAction(R.string.enable, view -> {
			if (isGiphyEnabledAndAllowed()) {
				updateUi();
			}
		});
		ThemeHelper.fix(this.snackBar);

	}

	@Override
	public void onRequestPermissionsResult(final int requestCode,
										   @NonNull final String[] permissions,
										   @NonNull final int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		updateUi();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	private boolean isGiphyEnabledAndAllowed() {
		return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) && this.isGiphyEnabled();
	}

	@Override
	protected void updateUi() {
		if (noAskAgain || isGiphyEnabledAndAllowed()) {
			this.snackBar.dismiss();
		} else {
			this.snackBar.show();
		}
	}
}