package delta.games.lotro.gui.character.status.tasks.statistics;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import delta.common.ui.swing.GuiFactory;
import delta.common.utils.l10n.L10n;
import delta.games.lotro.character.status.achievables.statistics.reputation.AchievablesReputationStats;
import delta.games.lotro.character.status.tasks.statistics.TasksStatistics;
import delta.games.lotro.common.statistics.items.ItemsStats;
import delta.games.lotro.gui.common.money.MoneyDisplayController;
import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * Controller for a panel to show the summary of the statistics about some achievables.
 * @author DAM
 */
public class TasksStatisticsSummaryPanelController
{
  // Data
  private TasksStatistics _statistics;
  // UI
  private JPanel _panel;
  private JLabel _distinctTasksCount;
  private JLabel _completionsCount;
  private JLabel _reputation;
  private JLabel _consumedItemsCount;
  private MoneyDisplayController _priceDisplay;
  private JLabel _earnedItemsCount;
  private JLabel _totalXP;
  private JLabel _totalItemXP;
  private JLabel _totalMountXP;

  /**
   * Constructor.
   * @param statistics Statistics to show.
   */
  public TasksStatisticsSummaryPanelController(TasksStatistics statistics)
  {
    _statistics=statistics;
    _panel=buildPanel();
    update();
  }

  private JPanel buildPanel()
  {
    JPanel panel=GuiFactory.buildPanel(new BorderLayout());
    // Stats panel
    JPanel statsPanel=GuiFactory.buildPanel(new GridBagLayout());
    TitledBorder border=GuiFactory.buildTitledBorder(Labels.getLabel("tasks.statistics.summary.border"));
    statsPanel.setBorder(border);
    panel.add(statsPanel,BorderLayout.CENTER);
    GridBagConstraints cLabels=new GridBagConstraints(0,0,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(2,5,2,0),0,0);
    GridBagConstraints cValues=new GridBagConstraints(1,0,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(2,5,2,5),0,0);

    // Distinct tasks count
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.distinctTasksCount")),cLabels);
    _distinctTasksCount=GuiFactory.buildLabel("");
    statsPanel.add(_distinctTasksCount,cValues);
    cLabels.gridy++;cValues.gridy++;
    // Completions count
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.completionsCount")),cLabels);
    _completionsCount=GuiFactory.buildLabel("");
    statsPanel.add(_completionsCount,cValues);
    cLabels.gridy++;cValues.gridy++;
    // Reputation
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.reputation")),cLabels);
    _reputation=GuiFactory.buildLabel("");
    statsPanel.add(_reputation,cValues);
    cLabels.gridy++;cValues.gridy++;
    // Consumed items
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.consumedItems")),cLabels);
    _consumedItemsCount=GuiFactory.buildLabel("");
    statsPanel.add(_consumedItemsCount,cValues);
    cLabels.gridy++;cValues.gridy++;
    // Price
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.value")),cLabels);
    _priceDisplay=new MoneyDisplayController();
    statsPanel.add(_priceDisplay.getPanel(),cValues);
    cLabels.gridy++;cValues.gridy++;
    // Earned items
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.earnedItems")),cLabels);
    _earnedItemsCount=GuiFactory.buildLabel("");
    statsPanel.add(_earnedItemsCount,cValues);
    cLabels.gridy++;cValues.gridy++;
    // XP
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.totalXP")),cLabels);
    _totalXP=GuiFactory.buildLabel("");
    statsPanel.add(_totalXP,cValues);
    cLabels.gridy++;cValues.gridy++;
    // Item XP
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.totalItemXP")),cLabels);
    _totalItemXP=GuiFactory.buildLabel("");
    statsPanel.add(_totalItemXP,cValues);
    cLabels.gridy++;cValues.gridy++;
    // Mount XP
    statsPanel.add(GuiFactory.buildLabel(Labels.getLabel("tasks.statistics.summary.field.totalMountXP")),cLabels);
    _totalMountXP=GuiFactory.buildLabel("");
    statsPanel.add(_totalMountXP,cValues);
    cLabels.gridy++;cValues.gridy++;

    return panel;
  }

  /**
   * Update display.
   */
  public void update()
  {
    // Distinct tasks count
    int distinctTasksCount=_statistics.getDistinctCompletedTasksCount();
    String distinctTasksCountStr=L10n.getString(distinctTasksCount);
    _distinctTasksCount.setText(distinctTasksCountStr);
    // Completions count
    int completionsCount=_statistics.getTaskCompletionsCount();
    String completionsCountStr=L10n.getString(completionsCount);
    _completionsCount.setText(completionsCountStr);
    // Reputation
    AchievablesReputationStats reputation=_statistics.getReputationStats();
    int nbFactions=reputation.getFactionsCount();
    String nbFactionsStr=L10n.getString(nbFactions);
    int nbReputationPoints=reputation.getTotalReputationPoints();
    String nbReputationPointsStr=L10n.getString(nbReputationPoints);
    String reputationStr=Labels.getLabel("tasks.statistics.reputation.format",new Object[]{nbReputationPointsStr,nbFactionsStr});
    _reputation.setText(reputationStr);
    // Consumed items
    ItemsStats consumedItemsStats=_statistics.getConsumedItemsStats();
    _consumedItemsCount.setText(getItemsLabel(consumedItemsStats));
    // Price
    _priceDisplay.setMoney(_statistics.getConsumedItemsPrice());
    // Earned items
    ItemsStats earnedItemsStats=_statistics.getEarnedItemsStats();
    _earnedItemsCount.setText(getItemsLabel(earnedItemsStats));
    // XP
    String totalXPStr=L10n.getString(_statistics.getTotalXP());
    _totalXP.setText(totalXPStr);
    String totalItemXPStr=L10n.getString(_statistics.getTotalItemXP());
    _totalItemXP.setText(totalItemXPStr);
    String totalMountXPStr=L10n.getString(_statistics.getTotalMountXP());
    _totalMountXP.setText(totalMountXPStr);
  }

  private String getItemsLabel(ItemsStats stats)
  {
    int nbItems=stats.getItemsCount();
    String nbItemsStr=L10n.getString(nbItems);
    int nbDistinctItems=stats.getDistinctItemsCount();
    String nbDistinctItemsStr=L10n.getString(nbDistinctItems);
    String ret=Labels.getLabel("tasks.statistics.items.format",new Object[]{nbItemsStr,nbDistinctItemsStr});
    return ret;
  }

  /**
   * Get the managed panel.
   * @return the managed panel.
   */
  public JPanel getPanel()
  {
    return _panel;
  }

  /**
   * Release all managed resources.
   */
  public void dispose()
  {
    // Data
    _statistics=null;
    // UI
    if (_panel!=null)
    {
      _panel.removeAll();
      _panel=null;
    }
    _distinctTasksCount=null;
    _completionsCount=null;
    _reputation=null;
    _consumedItemsCount=null;
    if (_priceDisplay!=null)
    {
      _priceDisplay.dispose();
      _priceDisplay=null;
    }
    _earnedItemsCount=null;
    _totalXP=null;
    _totalItemXP=null;
    _totalMountXP=null;
  }
}
