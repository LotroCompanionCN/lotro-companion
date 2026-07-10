package delta.games.lotro.gui.character.stats.curves;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import delta.common.ui.swing.GuiFactory;
import delta.games.lotro.character.CharacterData;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for a stat curve display panel (chart+values).
 * @author DAM
 */
public class StatCurvesPanelController
{
  // Controllers
  private StatCurveChartPanelController _chartPanel;
  private StatValuesPanelController _valuesPanel;
  private StatCurvesConfigurationPanel _configPanel;
  // UI
  private JPanel _panel;

  /**
   * Constructor.
   * @param config Curves configuration.
   */
  public StatCurvesPanelController(StatCurvesChartConfiguration config)
  {
    _chartPanel=new StatCurveChartPanelController(config);
    _valuesPanel=new StatValuesPanelController(config);
    _configPanel=new StatCurvesConfigurationPanel(config,_chartPanel,_valuesPanel);
    _panel=buildPanel();
  }

  /**
   * Get the managed panel.
   * @return the managed panel.
   */
  public JPanel getPanel()
  {
    return _panel;
  }

  private JPanel buildPanel()
  {
    JPanel panel=GuiFactory.buildPanel(new BorderLayout());
    // Chart
    JPanel chartPanel=_chartPanel.getPanel();
    TitledBorder chartBorder=GuiFactory.buildTitledBorder(Labels.getLabel("stat.curves.panel.border.chart"));
    chartPanel.setBorder(chartBorder);
    panel.add(chartPanel,BorderLayout.CENTER);

    JPanel southPanel=GuiFactory.buildPanel(new GridBagLayout());
    // Values
    JPanel valuesPanel=_valuesPanel.getPanel();
    TitledBorder valuesBorder=GuiFactory.buildTitledBorder(Labels.getLabel("stat.curves.panel.border.values"));
    valuesPanel.setBorder(valuesBorder);
    GridBagConstraints c=new GridBagConstraints(0,0,1,1,1.0,0.0,GridBagConstraints.NORTHWEST,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0);
    southPanel.add(valuesPanel,c);
    // Configuration
    JPanel configurationPanel=_configPanel.getPanel();
    TitledBorder configurationBorder=GuiFactory.buildTitledBorder(Labels.getLabel("stat.curves.panel.border.configuration"));
    configurationPanel.setBorder(configurationBorder);
    c=new GridBagConstraints(1,0,1,1,0.0,0.0,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0);
    southPanel.add(configurationPanel,c);
    panel.add(southPanel,BorderLayout.SOUTH);
    return panel;
  }

  /**
   * Update this panel with new stats.
   * @param toon Toon to display.
   */
  public void update(CharacterData toon)
  {
    _configPanel.update(toon.getLevel());
    _valuesPanel.update(toon.getStats());
    _chartPanel.update(toon.getStats());
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    if (_chartPanel!=null)
    {
      _chartPanel.dispose();
      _chartPanel=null;
    }
    if (_valuesPanel!=null)
    {
      _valuesPanel.dispose();
      _valuesPanel=null;
    }
    if (_configPanel!=null)
    {
      _configPanel.dispose();
      _configPanel=null;
    }
  }
}
