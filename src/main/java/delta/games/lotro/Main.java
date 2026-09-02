package delta.games.lotro;

import java.awt.Font;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import delta.common.ui.swing.GuiFactory;
import delta.common.ui.utils.exceptions.UIExceptionsLogger;
import delta.common.utils.l10n.L10nConfiguration;
import delta.common.utils.l10n.LocalizedFormats;
import delta.games.lotro.config.LotroCoreConfig;
import delta.games.lotro.dat.misc.Context;
import delta.games.lotro.gui.LotroIconsManager;
import delta.games.lotro.gui.main.MainFrameController;
import delta.games.lotro.utils.cfg.ApplicationConfiguration;
import delta.games.lotro.utils.gui.FontsManager;

/**
 * Main for LOTRO companion.
 * @author DAM
 */
public class Main
{
  /**
   * Main method of LOTRO companion.
   * @param args Not used.
   */
  public static void main(String[] args)
  {
    // Init UI
    UIExceptionsLogger.init();
    GuiFactory.init();
    // Register the bundled CJK font
    FontsManager.getInstance();
    // Prefer a CJK-capable UI font so that Chinese text does not fall back to SimSun
    setDefaultUiFont();
    // Init preferences
    GuiFactory.setPreferences(Config.getInstance().getPreferences());
    // Init l10n
    L10nConfiguration l10nCfg=ApplicationConfiguration.getInstance().getL10nConfiguration();
    LocalizedFormats.init(l10nCfg);
    Locale.setDefault(Locale.US);
    // Init app context
    Context.init(LotroCoreConfig.getMode());
    LotroCore.init();
    LotroIconsManager.initApplicationIcons();
    // Build main window
    MainFrameController controller=new MainFrameController();
    JFrame frame=controller.getFrame();
    frame.setVisible(true);
  }

  /**
   * Replace the default UI font (UIManager) by a CJK-capable one, if available.
   * Preserves the style and size of each UI default.
   */
  private static void setDefaultUiFont()
  {
    String family=FontsManager.getInstance().getPreferredCjkFontFamily();
    if (family==null)
    {
      return;
    }
    UIDefaults defs=UIManager.getDefaults();
    Enumeration<Object> keys=defs.keys();
    while (keys.hasMoreElements())
    {
      Object key=keys.nextElement();
      if ((key instanceof String) && ((String)key).endsWith(".font"))
      {
        Object value=defs.get(key);
        if (value instanceof Font)
        {
          Font font=(Font)value;
          defs.put(key,new Font(family,font.getStyle(),font.getSize()));
        }
      }
    }
  }
}
