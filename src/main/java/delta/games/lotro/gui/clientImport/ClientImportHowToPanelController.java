package delta.games.lotro.gui.clientImport;

import javax.swing.JComponent;
import javax.swing.JLabel;

import delta.common.utils.text.EndOfLine;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for the client import HOW TO panel.
 * @author DAM
 */
public class ClientImportHowToPanelController
{
  private JComponent _howTo;

  /**
   * Constructor.
   */
  public ClientImportHowToPanelController()
  {
    _howTo=buildHowToGadget();
  }

  /**
   * Get the managed gadget.
   * @return the managed gadget.
   */
  public JComponent getHowToGadget()
  {
    return _howTo;
  }

  private JLabel buildHowToGadget()
  {
    JLabel editor=new JLabel();
    String html=getHowToHtml();
    editor.setText(html);
    return editor;
  }

  private String getHowToHtml()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("<html><body>");
    String text=getHowToText();
    text=text.trim();
    text=text.replace("\n","<br>");
    sb.append(text);
    sb.append("</body></html>");
    return sb.toString();
  }

  private String getHowToText()
  {
    StringBuilder sb=new StringBuilder();
    sb.append(Labels.getLabel("clientimport.howto.step1"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.step2"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.step2a"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.step2a.detail"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.step2a.limit"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.or"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.step2b"));
    sb.append(EndOfLine.UNIX);
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.optionA.note"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.optionB.note"));
    sb.append(EndOfLine.UNIX);
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.vaults"));
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.vaults.detail"));
    sb.append(EndOfLine.UNIX);
    sb.append(EndOfLine.UNIX);
    sb.append(Labels.getLabel("clientimport.howto.housing"));
    sb.append(EndOfLine.UNIX);
    return sb.toString();
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    _howTo=null;
  }
}
