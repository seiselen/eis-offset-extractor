package app;

/** <b>(Enum of App Resource Adds)</b>. */
public enum ResVal {
  BUILDIR("build"),
  ASSETDIR("assets"),
  APPLOGO("app_logo.png"),
  APPICON("app_icon.png"),
  APPNAME("EisOffsetExtractor"),
  OUTSDIR("outputs"),
  OUTSFIX("_offsets");
  private String sPath;
  ResVal(){sPath=null;}
  ResVal(String in_sPath){sPath = in_sPath;}
  public String get(){return sPath==null ? this.toString() : sPath;}  
}