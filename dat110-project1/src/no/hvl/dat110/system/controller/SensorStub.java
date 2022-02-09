package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCLocalStub;
import no.hvl.dat110.rpc.RPCUtils;

public class SensorStub extends RPCLocalStub {

	private byte RPCIDREAD = 1;
	
	public SensorStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public int read() {
		
		// implement marshalling, call and unmarshalling for read RPC method
		
		int temp = 0;
		
		byte[] request = RPCUtils.marshallVoid();
		
		byte[] response = rpcclient.call(RPCIDREAD, request);
		
		temp = RPCUtils.unmarshallInteger(response);
		
		return temp;
	}
	
}
