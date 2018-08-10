package de.eqee.pn.xmpp;

import de.eqee.pn.entities.Account;
import de.eqee.pn.xmpp.stanzas.MessagePacket;

public interface OnMessagePacketReceived extends PacketReceived {
	public void onMessagePacketReceived(Account account, MessagePacket packet);
}
