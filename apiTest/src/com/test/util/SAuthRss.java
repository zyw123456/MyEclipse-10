package com.test.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public final class SAuthRss
{
	

  public static final String getIdentifier(String _ref)
    throws Exception
  {
    UFormat.checkEmptyParam("SAuthRss", "getIdentifier", new Object[] { _ref });

    return _ref;
  }

  public static final String getSeed(String _ref, SEED_TYPE _seedType)
    throws Exception
  {
    UFormat.checkEmptyParam("SAuthRss", "getSeed", new Object[] { _ref, _seedType });

    if (_seedType.equals(SEED_TYPE.CURRENTDAY)) return UFormat.date2str(new Date(), UFormat.DF_DATE.FORMAT21.getFormat());
    if (_seedType.equals(SEED_TYPE.ROT13MD5REF)) return UEncrypt.getHash(UEncrypt.HASH_TYPE.MD5, UEncrypt.rot13(_ref));
    throw new Exception("SAuthRss : No such seed type, failed to getSeed");
  }

  public static final String getSign(String _ref, UEncrypt.HASH_TYPE _hashType, String _hashedPwd, SEED_TYPE _seedType)
    throws Exception
  {
    UFormat.checkEmptyParam("SAuthRss", "getSign", new Object[] { _ref, _hashType, _hashedPwd, _seedType });

    return UEncrypt.getHash(_hashType, UFormat.buf2str(new String[] { "ref=", _ref, "&password=", _hashedPwd, "&seed=", getSeed(_ref, _seedType) }));
  }

 
 
  

 

  





  public static final class AuthEntity
  {
    public String ref;
    public UEncrypt.HASH_TYPE hashType;
    public String hashedPwd;
    public SAuthRss.SEED_TYPE seedType;

    public AuthEntity()
    {
    }

    public AuthEntity(String _ref, UEncrypt.HASH_TYPE _hashType, String _hashedPwd, SAuthRss.SEED_TYPE _seedType)
    {
      this.ref = _ref;
      this.hashType = _hashType;
      this.hashedPwd = _hashedPwd;
      this.seedType = _seedType;
    }
    @JsonIgnore
    public final String getIdentifier() throws Exception { return SAuthRss.getIdentifier(this.ref); } 
    @JsonIgnore
    public final String getDesc() throws Exception { return UFormat.buf2str(new String[] { this.ref, "|", this.hashType.name(), "|", this.hashedPwd, "|", this.seedType.name(), "|", getSign() }); } 
    @JsonIgnore
    public final String getSign() throws Exception { return SAuthRss.getSign(this.ref, this.hashType, this.hashedPwd, this.seedType); }

  }

  public static enum SEED_TYPE
  {
    CURRENTDAY, ROT13MD5REF;
  }
}
