package delta.games.lotro.gui.misc.paypal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import delta.common.ui.swing.GuiFactory;
import delta.common.ui.swing.labels.BrowserHyperlinkAction;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for a paypal 'donate' button.
 * @author DAM
 */
public class PaypalButtonController
{
  private JButton _button;

  /**
   * Constructor.
   */
  public PaypalButtonController()
  {
    _button=build();
  }

  /**
   * Get the managed button.
   * @return the managed button.
   */
  public JButton getButton()
  {
    return _button;
  }

  private JButton build()
  {
    JButton button=GuiFactory.buildIconButton("/resources/gui/paypal/donate.png");
    // Tooltip
    String tooltip=Labels.getLabel("misc.paypal.donate.tooltip");
    button.setToolTipText(tooltip);
    // Donate
    ActionListener al=new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String donatePaypal="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=CG39NLT2NJ58A&source=url";
        BrowserHyperlinkAction action=new BrowserHyperlinkAction(donatePaypal,Labels.getLabel("misc.paypal.donate.label"));
        action.doClick(null);
      }
    };
    button.addActionListener(al);
    return button;
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    _button=null;
  }
}
