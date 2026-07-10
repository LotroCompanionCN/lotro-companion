package delta.games.lotro.gui.character.status.traits.racial;

import javax.swing.JDialog;
import javax.swing.JPanel;

import delta.common.ui.swing.windows.DefaultFormDialogController;
import delta.common.ui.swing.windows.WindowController;
import delta.games.lotro.character.BasicCharacterAttributes;
import delta.games.lotro.character.status.traits.shared.TraitSlotsStatus;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for the racial traits edition dialog.
 * @author DAM
 */
public class RacialTraitsEditionDialogController extends DefaultFormDialogController<TraitSlotsStatus>
{
  private RacialTraitsEditionPanelController _traitsEdition;

  /**
   * Constructor.
   * @param parentController Parent controller.
   * @param status Traits to edit.
   * @param character Character attributes.
   */
  public RacialTraitsEditionDialogController(WindowController parentController, TraitSlotsStatus status, BasicCharacterAttributes character)
  {
    super(parentController,status);
    _traitsEdition=new RacialTraitsEditionPanelController(this,character,status);
    _traitsEdition.setTraits(status);
  }

  @Override
  protected JDialog build()
  {
    JDialog dialog=super.build();
    dialog.setTitle(Labels.getLabel("traits.racial.edition.dialog.title"));
    dialog.setResizable(false);
    dialog.pack();
    return dialog;
  }

  @Override
  protected JPanel buildFormPanel()
  {
    JPanel traitsPanel=_traitsEdition.getPanel();
    return traitsPanel;
  }

  @Override
  public void okImpl()
  {
    _data=_traitsEdition.getTraits();
  }

  /**
   * Release all managed resources.
   */
  @Override
  public void dispose()
  {
    super.dispose();
    if (_traitsEdition!=null)
    {
      _traitsEdition.dispose();
      _traitsEdition=null;
    }
  }
}
