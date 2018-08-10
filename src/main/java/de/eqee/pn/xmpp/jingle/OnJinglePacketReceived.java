package de.eqee.pn.xmpp.jingle;

import de.eqee.pn.entities.Account;
import de.eqee.pn.xmpp.PacketReceived;
import de.eqee.pn.xmpp.jingle.stanzas.JinglePacket;

public interface OnJinglePacketReceived extends PacketReceived {
	void onJinglePacketReceived(Account account, JinglePacket packet);
}
