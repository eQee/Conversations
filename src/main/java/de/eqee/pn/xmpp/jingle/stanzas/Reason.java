package de.eqee.pn.xmpp.jingle.stanzas;

import de.eqee.pn.xml.Element;

public class Reason extends Element {
	private Reason(String name) {
		super(name);
	}

	public Reason() {
		super("reason");
	}
}
