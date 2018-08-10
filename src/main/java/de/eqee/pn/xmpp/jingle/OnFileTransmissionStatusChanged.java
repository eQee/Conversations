package de.eqee.pn.xmpp.jingle;

import de.eqee.pn.entities.DownloadableFile;

public interface OnFileTransmissionStatusChanged {
	void onFileTransmitted(DownloadableFile file);

	void onFileTransferAborted();
}
