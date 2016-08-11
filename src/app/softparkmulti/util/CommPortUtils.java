package app.softparkmulti.util;

import jssc.SerialPortList;

public class CommPortUtils {

	public CommPortUtils() {
		// TODO Auto-generated constructor stub
	}
	
	
	protected static String[] getSerialPorts(){
			String[] portEnum = SerialPortList.getPortNames();
			return portEnum;
	}

}
