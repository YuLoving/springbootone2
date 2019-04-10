package cn.nj.springbootone.utils;

/**
 * The Class ZezsException.
 */
public class ZezsException extends RuntimeException{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5960587297457353119L;
	
	/** The error code. */
//	protected int code=999;
	protected String code="999";
    public ZezsException(String message) {
		super(message);
	}
    
    public ZezsException(String code,String message) {
		super(message);
		this.code=code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
    
}
