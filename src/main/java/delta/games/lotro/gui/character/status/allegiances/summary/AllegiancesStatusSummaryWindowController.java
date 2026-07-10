package delta.games.lotro.gui.character.status.allegiances.summary;

import javax.swing.JDialog;
import javax.swing.JPanel;

import delta.common.ui.swing.windows.DefaultDisplayDialogController;
import delta.common.ui.swing.windows.WindowController;
import delta.games.lotro.character.status.allegiances.AllegiancesStatusManager;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for a window that displays the status of allegiances for a character.
 * @author DAM
 */
public class AllegiancesStatusSummaryWindowController extends DefaultDisplayDialogController<AllegiancesStatusManager>
{
  /**
   * Window identifier.
   */
  public static final String IDENTIFIER="ALLEGIANCES_STATUS";

  // Controllers
  private AllegiancesStatusSummaryPanelController _statusController;

  /**
   * Constructor.
   * @param parent Parent window.
   * @param status Data to show.
   */
  public AllegiancesStatusSummaryWindowController(WindowController parent, AllegiancesStatusManager status)
  {
    super(parent,status);
    _statusController=new AllegiancesStatusSummaryPanelController(this,status);
  }

  @Override
  protected JDialog build()
  {
    JDialog dialog=super.build();
    dialog.setTitle(Labels.getLabel("character.status.allegiances.summary.window.title"));
    dialog.pack();
    dialog.setResizable(false);
    return dialog;
  }

  @Override
  public String getWindowIdentifier()
  {
    return IDENTIFIER;
  }

  @Override
  public void configureWindow()
  {
    automaticLocationSetup();
  }

  @Override
  protected JPanel buildFormPanel()
  {
    JPanel panel=_statusController.getPanel();
    return panel;
  }

  /**
   * Release all managed resources.
   */
  @Override
  public void dispose()
  {
    super.dispose();
    // Data
    _data=null;
    // Controllers
    if (_statusController!=null)
    {
      _statusController.dispose();
      _statusController=null;
    }
  }
}
