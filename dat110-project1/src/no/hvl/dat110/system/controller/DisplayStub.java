package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCLocalStub;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayStub extends RPCLocalStub {
		
	private byte RPCIDDISPLAY = 2;
	
	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		byte[] request = RPCUtils.marshallString(message);
		
		byte[] response = rpcclient.call(RPCIDDISPLAY, request);
		
		RPCUtils.unmarshallVoid(response);
		
	}
}
