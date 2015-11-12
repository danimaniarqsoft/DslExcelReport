DSL Framework for Apache Poi
==============================

Introduction
------------------------------
The present project try to make the Excel development easy through Fluent Api for create Excel documents. The core Api for the DSL [^fluentApi] is based on [Apache POI](http://poi.apache.org/ "Apache POI Home").

Excel Column Annotation
------------------------------
The engine of the DSL for create Excel documents use the `@ExcelColumn` annotation to create the whole Document. Basically, you have to decorate the attributes on your class with the Annotation and add properties that will map on the cell.

Let's to build a excel document like it show next:

| Persona |  Cantidad |  Edad | Fecha 
--------- | --------- | ----- | --------
Cristiano Ronaldo   | 1344.567   |  29   | 27/09/2014
Messi   | 2344.567   |  27   | 27/09/2014
Bale   | 544.567   |  25   | 27/09/2014

The steps for create a document are shown next:
> **Steps:**

> 1. Create a JavaBean Class. and add an **`@ExcelAnnotation`** on each property of the class property where we want to map in the excel document.
>1. Configure the The DSL for create the Document.

###Create a JavaBean Class###

The Pojo created is shown next:

```
public class Persona {

    @ExcelColumn(name = "Persona")
	private String persona;

	@ExcelColumn(name = "Cantidad")
	private double cantidad;

	@ExcelColumn(name = "Edad")
	private int edad;

	@ExcelColumn(name = "Fecha")
	private Date fecha;
	
   //Getters and Setters methods
     .
     .
```
###Configure the The DSL for create the Document.###

Here I show a snipped of code for create a document Based on the DSL and `@ExcelColumn` annotation:

```
import static com.danimaniarqsoft.report.poi.dsl.WorkbookBuilder.createWorkbook;
Workbook wb = createWorkbook(WorkbookEnum.XLSX)
              .createSheet("hoja1")
              .createHeader(Persona.class)
              .createRows(personas, Persona.class)
              .buildWorkbook();
```

### @ExcelAnnotation####

The `@ExcelAnnotation` has a lot of properties for control the way that the DSL have to build the Excel Document. The next table describe each property:

Aligments

Property    | Description   | Default
----------- | ------------- | -------
 name       | The name of the column | nothing, it is mandatory
dateFormat | This property is used for java.util.Date type. The value is a String with the date format defined on [Java Date Format](http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) | `"dd/MM/YYYY"`
textPosition | this property is used for align vertical text both Horizontally and vertically on a Cell | `TextPosition.ALIGN_CENTER` 
fontFormat | this property is used for font format | `FontFormat.NORMAL`


[^fluentApi]:**Fluent Interface**, [Martin Fowler](http://martinfowler.com/bliki/FluentInterface.html) on December twenty of 2005 write about a certain style of interface which he decided to name **fluent interface**. We write DSL's based on the concept of **Fluent Interface**


&copy; danimaniArqsoft
