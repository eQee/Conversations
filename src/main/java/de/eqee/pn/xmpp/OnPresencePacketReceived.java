package de.eqee.pn.xmpp;

import de.eqee.pn.entities.Account;
import de.eqee.pn.xmpp.stanzas.PresencePacket;

public interface OnPresencePacketReceived extends PacketReceived {
	public void onPresencePacketReceived(Account account, PresencePacket packet);
}
