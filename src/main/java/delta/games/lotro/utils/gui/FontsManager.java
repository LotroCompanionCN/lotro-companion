package delta.games.lotro.utils.gui;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom fonts manager.
 * <p>Registers the bundled CJK fonts so that Chinese text can be rendered
 * with a consistent font across all platforms.
 * @author DAM
 */
public final class FontsManager
{
  private static final Logger LOGGER=LoggerFactory.getLogger(FontsManager.class);

  private static final List<String> FONTS = Arrays.asList(
      "/resources/gui/fonts/NotoSansCJKsc-Regular.otf",
      "/resources/gui/fonts/NotoSansCJKsc-Bold.otf");

  /**
   * Preferred CJK font families, best match first.
   */
  private static final String[] PREFERRED_FONT_FAMILIES = new String[] {
    "Microsoft YaHei UI",
    "Microsoft YaHei",
    "PingFang SC",
    "Noto Sans CJK SC",
    "Noto Sans SC"
  };

  private static FontsManager _instance=new FontsManager();

  private String _fontFamily;
  private String _preferredFamily;

  /**
   * Get the sole instance of this class.
   * @return the fonts manager.
   */
  public static FontsManager getInstance()
  {
    return _instance;
  }

  private FontsManager()
  {
    loadFonts();
  }

  private void loadFonts()
  {
    GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    for(String fontResource : FONTS)
    {
      try
      {
        URL ufont=FontsManager.class.getResource(fontResource);
        if (ufont==null)
        {
          LOGGER.warn("Could not find bundled font resource: "+fontResource);
          continue;
        }
        URLConnection con=ufont.openConnection();
        con.connect();
        InputStream is=con.getInputStream();
        try
        {
          Font f=Font.createFont(Font.TRUETYPE_FONT,is);
          ge.registerFont(f);
          if (_fontFamily==null)
          {
            _fontFamily=f.getFamily();
          }
        }
        finally
        {
          is.close();
        }
      }
      catch(Exception e)
      {
        LOGGER.warn("Could not load bundled font "+fontResource,e);
      }
    }
  }

  /**
   * Get the family name of the bundled CJK font, if loaded.
   * @return a font family name, or <code>null</code> if the font could not be loaded.
   */
  public String getFontFamily()
  {
    return _fontFamily;
  }

  /**
   * Get the family name of a CJK-capable font to use for rendering Chinese text.
   * <p>Prefer an installed system font (e.g. Microsoft YaHei on Windows),
   * and fall back to the bundled Noto Sans CJK font.
   * @return A font family name, or <code>null</code> if none is available.
   */
  public String getPreferredCjkFontFamily()
  {
    if (_preferredFamily==null)
    {
      _preferredFamily=findPreferredFamily();
      if (_preferredFamily==null)
      {
        _preferredFamily=_fontFamily;
      }
    }
    return _preferredFamily;
  }

  /**
   * Find the first preferred CJK font family that is installed on this machine.
   * @return A font family name, or <code>null</code> if none is available.
   */
  private String findPreferredFamily()
  {
    String[] installed=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    for(String preferred : PREFERRED_FONT_FAMILIES)
    {
      for(String name : installed)
      {
        if (preferred.equalsIgnoreCase(name))
        {
          return preferred;
        }
      }
    }
    return null;
  }
}
