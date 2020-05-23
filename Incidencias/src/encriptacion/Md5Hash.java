package encriptacion;

import java.security.MessageDigest;
import java.sql.SQLException;

public class Md5Hash {

	
public static String rMd5Hash(String clave) throws SQLException, Exception {
	MessageDigest myDigest = MessageDigest.getInstance("MD5");
	myDigest.update(clave.getBytes());
	byte[] dataBytes = myDigest.digest();
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < dataBytes.length; i++) {
		sb.append(Integer.toString((dataBytes[i])).substring(1));
	}
	StringBuffer hexString = new StringBuffer();
	for (int i = 0; i < dataBytes.length; i++) {
		String hex = Integer.toHexString(0xff & dataBytes[i]);
		if (hex.length() == 1)
			hexString.append('0');
		hexString.append(hex);
	}
	String retParam = hexString.toString();
	return retParam;

}

}
