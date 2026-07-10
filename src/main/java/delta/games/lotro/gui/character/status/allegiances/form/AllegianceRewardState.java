package delta.games.lotro.gui.character.status.allegiances.form;

import delta.games.lotro.gui.utils.l10n.Labels;

/**
 * State of an allegiance reward.
 * @author DAM
 */
public enum AllegianceRewardState
{
  /**
   * Future.
   */
  FUTURE,
  /**
   * Unlocked (but not claimed).
   */
  UNLOCKED,
  /**
   * Claimed.
   */
  CLAIMED;

  /**
   * Get the display label.
   * @return the display label.
   */
  public String getLabel()
  {
    switch (this)
    {
      case FUTURE:
        return Labels.getLabel("rewards.track.step.future");
      case UNLOCKED:
        return Labels.getLabel("rewards.track.step.unlocked");
      case CLAIMED:
        return Labels.getLabel("rewards.track.step.claimed");
      default:
        return "";
    }
  }
}
