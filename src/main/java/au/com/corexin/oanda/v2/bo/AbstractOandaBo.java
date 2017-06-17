package au.com.corexin.oanda.v2.bo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public abstract class AbstractOandaBo implements Serializable{

	public String code = "";
	public String message = "";

	public boolean hasErrors() {
		return !StringUtils.isEmpty(code) || !StringUtils.isEmpty(message);
	}
}
