package chart;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;
//This class create a line chart base on input
public class ChartCreator extends ApplicationFrame {
   //Initial method
   public ChartCreator( final String title, double[] out ) {
      super( title );         
      final XYDataset dataset = createDataset(out);         
      final JFreeChart chart = createChart( dataset );         
      final ChartPanel chartPanel = new ChartPanel( chart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );         
      chartPanel.setMouseZoomable( true , false );         
      setContentPane( chartPanel );
   }
   //This method create dataset for the chart from a double array
   private XYDataset createDataset(double[] out) {
      final TimeSeries series = new TimeSeries( "Random Data");
      Minute current = new Minute(0, 0, 1, 1, 2000);
      for (int i = 0; i < out.length; i++) {
         try {              
            series.add(current, out[i] );                 
            current = ( Minute ) current.next(); 
         } catch ( SeriesException e ) {
            System.err.println("Error adding to series");
         }
      }
       
      return new TimeSeriesCollection(series);
   }     
   //This method create the chart
   private JFreeChart createChart( final XYDataset dataset ) {
      JFreeChart chart = ChartFactory.createTimeSeriesChart(             
         "Computing Test", 
         "Hour",              
         "kWh",              
         dataset,             
         false,              
         false,              
         false);
      XYPlot plot = (XYPlot) chart.getPlot();
      DateAxis dateaxis= (DateAxis) plot.getDomainAxis();
      dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.HOUR, 4, new SimpleDateFormat("HH:MM")));
      return chart;
   }
   //This method draw and make the chart visible
   public void draw(String title, double[] out) {
	  final ChartCreator demo = new ChartCreator(title, out);         
	  demo.pack( );         
	  RefineryUtilities.positionFrameRandomly( demo );         
	  demo.setVisible( true );
   }
}   