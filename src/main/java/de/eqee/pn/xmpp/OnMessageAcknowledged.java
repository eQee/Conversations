package de.eqee.pn.xmpp;

import de.eqee.pn.entities.Account;

public interface OnMessageAcknowledged {
	boolean onMessageAcknowledged(Account account, String id);
}
