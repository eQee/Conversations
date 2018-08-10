package de.eqee.pn.xmpp;

import de.eqee.pn.entities.Account;
import de.eqee.pn.xmpp.stanzas.IqPacket;

public interface OnIqPacketReceived extends PacketReceived {
	void onIqPacketReceived(Account account, IqPacket packet);
}
