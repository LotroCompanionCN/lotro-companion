package delta.games.lotro.gui.charts;

import java.awt.Font;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;

import delta.games.lotro.utils.gui.FontsManager;

/**
 * Helper to force CJK-capable fonts on JFreeChart charts.
 * <p>JFreeChart uses its own default fonts (e.g. Tahoma) which do not contain
 * Chinese glyphs, so localized text renders as squares. This class reapplies
 * the font family used by the rest of the UI on the chart text elements.
 * @author DAM
 */
public class JFreeChartFonts
{
  /**
   * Apply a CJK-capable font to the text of the given chart (title, legend,
   * axis labels and tick labels, pie labels), preserving each element's size.
   * @param chart Chart to update.
   */
  public static void applyCjkFonts(JFreeChart chart)
  {
    if (chart==null)
    {
      return;
    }
    String family=FontsManager.getInstance().getPreferredCjkFontFamily();
    if (family==null)
    {
      return;
    }
    // Title
    TextTitle title=chart.getTitle();
    if (title!=null)
    {
      title.setFont(withFamily(title.getFont(),family));
    }
    // Legend
    LegendTitle legend=chart.getLegend();
    if (legend!=null)
    {
      legend.setItemFont(withFamily(legend.getItemFont(),family));
    }
    // Plot
    Plot plot=chart.getPlot();
    if (plot instanceof XYPlot)
    {
      XYPlot xyPlot=(XYPlot)plot;
      applyAxisFont(xyPlot.getDomainAxis(),family);
      applyAxisFont(xyPlot.getRangeAxis(),family);
    }
    else if (plot instanceof PiePlot)
    {
      PiePlot piePlot=(PiePlot)plot;
      piePlot.setLabelFont(withFamily(piePlot.getLabelFont(),family));
    }
  }

  private static void applyAxisFont(ValueAxis axis, String family)
  {
    if (axis==null)
    {
      return;
    }
    axis.setLabelFont(withFamily(axis.getLabelFont(),family));
    axis.setTickLabelFont(withFamily(axis.getTickLabelFont(),family));
  }

  private static Font withFamily(Font font, String family)
  {
    if (font==null)
    {
      return new Font(family,Font.PLAIN,12);
    }
    return new Font(family,font.getStyle(),font.getSize());
  }
}
