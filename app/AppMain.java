package app;
import PrEis.utils.FileSysUtils;
import PrEis.utils.JAResourceUtil;
import PrEis.utils.ZScriptUtils;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.io.File;

public class AppMain extends PApplet {
  public static int CANVAS_WIDE = 768;
  public static int CANVAS_TALL = 192;
  public static int CANVAS_WIDH = CANVAS_WIDE/2;
  public static int CANVAS_TALH = CANVAS_TALL/2;

  public static String assetPath;
  public static String rootPath;  
  public AppGUI agui;
  public int FILL_BG;

  public static void main(String[] args) {PApplet.main("app.AppMain"); System.out.println("\n \n");}
  
  public void settings(){this.size(CANVAS_WIDE, CANVAS_TALL);}
  
  public void setup(){
    initAssetPath();
    initRootPath();
    JAResourceUtil.app = this;
    getSurface().setIcon(loadImage(AppMain.fullPathInAssetDir(ResVal.APPICON)));
    getSurface().setTitle(ResVal.APPNAME.get());
    agui = new AppGUI(this);
    FILL_BG = color(16);
  }

  public void draw(){background(FILL_BG); agui.update(); agui.render();}
  public void mouseWheel(MouseEvent e){agui.onMouseWheel(e.getCount());}
  public void mousePressed(){agui.onMousePressed();}
  public void keyPressed(){if(key=='q'||key=='Q'||keyCode==PApplet.ESC){exit(); return;}}

  public void initAssetPath(){
    File f = new File(FileSysUtils.pathConcat(sketchPath(), ResVal.ASSETDIR.get()));
    if (f.exists() && f.isDirectory()){AppMain.assetPath = f.getAbsolutePath();}
    f = new File(FileSysUtils.pathConcat(sketchPath(), ResVal.BUILDIR.get(), ResVal.ASSETDIR.get()));
    if (f.exists() && f.isDirectory()){AppMain.assetPath = f.getAbsolutePath();}
  }

  public void initRootPath(){
    File f = new File(FileSysUtils.pathConcat(sketchPath(), ResVal.BUILDIR.get())); 
    if (f.exists() && f.isDirectory()){AppMain.rootPath = f.getAbsolutePath();}
    else{AppMain.rootPath = sketchPath();}
  }

  /** Returns path concat of {@link #assetPath} with input subpath therefrom. */
  public static String fullPathInAssetDir(ResVal sp){return FileSysUtils.pathConcat(AppMain.assetPath,sp.get());}

  /** Returns path concat of {@link #rootPath} with input subpath therefrom. */
  public static String fullPathInRootDir(ResVal sp){return FileSysUtils.pathConcat(AppMain.rootPath,sp.get());}

  public void onSelOffRootDir(File selFile){
    if(selFile==null){return;}
    String tarPath = selFile.getAbsolutePath();
    String outName = FileSysUtils.fnameFromFpath(FileSysUtils.winPthToLinuxPth(tarPath))+ResVal.OUTSFIX.get();
    String outPath = FileSysUtils.pathConcat(AppMain.rootPath, ResVal.OUTSDIR.get(), outName);
    ZScriptUtils.extractAllSpriteOffsetsIn(this, tarPath, outPath);
  }

}