package app;
import PrEis.gui.AppFont;
import PrEis.gui.IActionCallback;
import PrEis.gui.UIAppBar;
import PrEis.gui.UIClick;
import PrEis.gui.UIManager;
import PrEis.gui.UIObject;
import PrEis.gui.WidgetType;
import PrEis.utils.BBox;
import PrEis.utils.JAResourceUtil;
import PrEis.utils.PrEisRes;
import processing.core.PApplet;
import processing.core.PVector;

public class AppGUI {
  PApplet app;
  UIManager uim;
  AppFont FNT = AppFont.TEXT;

  public final String LBL = "Select Directory To Extract Offsets From";

  public AppGUI(PApplet iApp){
    app = iApp;
    uim = new UIManager(app);
    UIObject.textFont = JAResourceUtil.getFontFromJAR(PrEisRes.TXT_FONT);
    UIObject.symbFont = JAResourceUtil.getFontFromJAR(PrEisRes.SYM_FONT);
    init();
  }

  public void init(){

    class OffRootDirAct implements IActionCallback {
      @Override public void action() {
        app.selectFolder(LBL, "onSelOffRootDir");
      }
    }

    uim.bindUiObject(
      new UIAppBar(app, WidgetType.NA)
      .bindAppLogoΘ(
        app.loadImage(AppMain.fullPathInAssetDir(ResVal.APPLOGO)),
        new PVector(600, 64)
      )
      .setStyleProp("fill", Integer.class, app.color(32))
    );

    UIClick.create(uim, new BBox((AppMain.CANVAS_WIDH)-320,88,640,48), LBL, FNT, new OffRootDirAct())
    .setStyleProp("fill",          Integer.class, app.color(0, 160, 160))
    .setStyleProp("fill_hovered",  Integer.class, app.color(0, 192, 192))
    .setStyleProp("fill_clicked",  Integer.class, app.color(0, 224, 224))
    ;
  }

  public void onMousePressed(){uim.onMousePressed();}
  public void onMouseWheel(int v){uim.onMouseWheel(v);}
  public void update(){uim.update();}
  public void render(){uim.render();}
}
