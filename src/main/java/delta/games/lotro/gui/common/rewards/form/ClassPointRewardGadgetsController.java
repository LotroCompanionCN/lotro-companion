package delta.games.lotro.gui.common.rewards.form;

import java.awt.Color;

import javax.swing.Icon;

import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.area.AreaController;
import delta.common.ui.swing.icons.IconsManager;
import delta.common.ui.swing.labels.LabelWithHalo;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for the UI gadgets of a class points reward.
 * @author DAM
 */
public class ClassPointRewardGadgetsController extends RewardGadgetsController
{
  /**
   * Constructor.
   * @param parent Parent controller.
   * @param count Class points count.
   */
  public ClassPointRewardGadgetsController(AreaController parent, int count)
  {
    super(parent);
    // Label
    String text;
    if (count>1)
    {
      text=Labels.getLabel("common.rewards.classPointsFormat",new Object[]{String.valueOf(count)});
    }
    else
    {
      text=Labels.getLabel("common.rewards.classPoint");
    }
    Color color=Color.WHITE;
    _label=new LabelWithHalo();
    _label.setText(text);
    _label.setOpaque(false);
    _label.setForeground(color);
    // Icon
    Icon lpIcon=IconsManager.getIcon("/resources/gui/icons/ClassPoint.png");
    _icon=GuiFactory.buildIconLabel(lpIcon);
  }
}
