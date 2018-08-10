package de.eqee.pn.xmpp;

import de.eqee.pn.entities.Contact;

public interface OnContactStatusChanged {
	public void onContactStatusChanged(final Contact contact, final boolean online);
}
