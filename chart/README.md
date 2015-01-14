DSL Framework for JFreeChart
==============================

Introduction
------------------------------
The present project try to make the Chart generation easy through a Fluent Api [^fluentApi]. The core of the Api is based on [JFreechart](http://www.jfree.org/ "JFreechart Home").

@Chart Annotation
------------------------------
The engine of the DSL for create Charts use the `@Chart` annotation for create the whole Chart. Basically, you have to decorate the class on your class with the Annotation and add properties that will map on graph properties.

Let's to build a graph of "amount vs age " with the next information:

| Player |  Mount |  Age | Date 
--------- | --------- | ----- | --------
Cristiano Ronaldo   | 1344.567   |  29   | 27/09/2014
Messi   | 2344.567   |  27   | 27/09/2014
Bale   | 544.567   |  25   | 27/09/2014

The steps for create a graph are shown next:
>  **Steps:**

> 1. Create a JavaBean Class. and add an **`@Graph`** Annotation on top Of the class.
> 2. Configure the The DSL for create the Graph

###Create a JavaBean Class###

The Pojo created is shown next:

```

@Chart(chartType = ChartType.PIE_CHART, xProperty = "edad", yProperty = "cantidad")
public class Persona {

	private String persona;
	
	private double cantidad;

	private int edad;

	private Date fecha;
	
   //Getters and Setters methods
     .
     .
```
###Configure the The DSL for create the Graph.###

Here I show a snipped of code for create a graph Based on the DSL and `@Graph` annotation:

```
import static com.danimaniarqsoft.report.poi.dsl.WorkbookBuilder.createWorkbook;
    private List<Persona> personasDataset;
     //Fill personasDataset from some datasource
    JFreeChart chart = createChart().ofTypeXYPlotChart()
	    				.withXAxisLabel("X-edad").
		    			withYAxisLabel("Y-cantidad")
			    		.withChartTitle("Reflection")
				    	.addDataSet(personasDataset, Persona.class).
					    createChart();
					
    ChartUtilities.saveChartAsJPEG(new File("ReflectionChart.jpg"),
```

### @Graph Annotation####

The `@Graph` annotation has a lot of properties for control the way how the DSL have to build the graph. The next table describe each property:

Aligments

Property    | Description   | Default
----------- | ------------- | -------
 name       | The name of the column | nothing, it is mandatory
chartType | This property is used for choose the kind of Graph we would like to build. The value is defined in the Enum `com.danimaniarqsoft.report.chart.dsl.ChartType` | nothing, it is mandatory
xProperty | In this property we have to provide the correct name of the instance class that we want to use for print a point into the graph, This property represent the x coordinate of the point that will be printed in the graph  | nothing, it is mandatory
[^fluentApi]:**Fluent Interface**, [Martin Fowler](http://martinfowler.com/bliki/FluentInterface.html) on December twenty of 2005 write about a certain style of interface which he decided to name **fluent interface**. We write DSL's based on the concept of **Fluent Interface**


&copy; danimaniArqsoft
