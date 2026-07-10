package delta.games.lotro.gui.utils.items;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.menus.CommandExecutor;
import delta.common.ui.swing.menus.PopupMenuController;
import delta.common.ui.swing.misc.Disposable;
import delta.games.lotro.dat.utils.DatIconsUtils;
import delta.games.lotro.gui.utils.icons.ItemIconBuilder;
import delta.games.lotro.gui.utils.l10n.Labels;
import delta.games.lotro.lore.items.Item;
import delta.games.lotro.utils.gui.filechooser.FileChooserController;

/**
 * Controller for a "save item icon" pop-up menu.
 * @author DAM
 */
public class SaveItemIconController implements CommandExecutor,Disposable
{
  private static final Logger LOGGER=LoggerFactory.getLogger(SaveItemIconController.class);

  private static final String ID="saveItemIcon";
  private static final String SAVE_ICON_TO_FILE="SAVE_ICON_TO_FILE";
  private PopupMenuController _menu;
  private Item _item;
  private JComponent _component;

  /**
   * Constructor.
   * @param item Item to use.
   * @param component Parent component.
   */
  public SaveItemIconController(Item item, JComponent component)
  {
    _item=item;
    _component=component;
    installMenu();
  }

  private void installMenu()
  {
    _menu=new PopupMenuController(this);
    _menu.addMenuItem(Labels.getLabel("item.icon.save.menu"),SAVE_ICON_TO_FILE);
    _menu.install(_component);
  }

  @Override
  public void invoke(String command)
  {
    if (SAVE_ICON_TO_FILE.equals(command))
    {
      doSaveIcon();
    }
  }

  private void doSaveIcon()
  {
    FileChooserController ctrl=new FileChooserController(ID, Labels.getLabel("item.icon.save.filechooser.title"));
    // Setup default file
    String filename=_item.getIcon()+".png";
    ctrl.getChooser().setSelectedFile(new File(filename));
    // Choose file...
    File toFile=ctrl.chooseFile(_component,"Save");
    if (toFile!=null)
    {
      boolean doIt=true;
      if (!toFile.getName().toLowerCase().endsWith(".png"))
      {
        toFile=new File(toFile.getParentFile(),toFile.getName()+".png");
      }
      if (toFile.exists())
      {
        doIt=false;
        int result=GuiFactory.showQuestionDialog(_component,Labels.getLabel("item.icon.save.overwrite.question"),Labels.getLabel("item.icon.save.overwrite.title"),JOptionPane.YES_NO_OPTION);
        if (result==JOptionPane.OK_OPTION)
        {
          doIt=true;
        }
      }
      if (doIt)
      {
        boolean ok=saveToFile(toFile);
        if (!ok)
        {
          GuiFactory.showErrorDialog(_component,Labels.getLabel("item.icon.save.export.failed"),Labels.getLabel("item.icon.save.export.error.title"));
        }
      }
    }
  }

  private boolean saveToFile(File toFile)
  {
    boolean ok=false;
    try
    {
      ItemIconBuilder builder=new ItemIconBuilder();
      BufferedImage image=builder.getItemIcon(_item.getIcon());
      ok=DatIconsUtils.writeImage(image,toFile);
    }
    catch(Exception e)
    {
      LOGGER.warn("Could not save item icon to file: "+toFile, e);
    }
    return ok;
  }

  @Override
  public void dispose()
  {
    if (_menu!=null)
    {
      _menu.dispose();
      _menu=null;
    }
  }
}
