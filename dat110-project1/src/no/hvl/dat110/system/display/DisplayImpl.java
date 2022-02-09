package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayImpl extends RPCRemoteImpl {

	public DisplayImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid,rpcserver);
	}
	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] params) {
		
		// implement unmarshalling, call, and marshall for write RPC method
		// look at how this is done in the SensorImpl class for the read method
		
		String temp = RPCUtils.unmarshallString(params);
		
		write(temp);
		
		byte[] reply = RPCUtils.marshallVoid();		
		
		return reply;
	}
}
