<HTML>

<%@ page import="java.util.*" %>
<%@ page import="Model.Automobile" %>
<%@ page import="Car.*" %>

<BODY bgcolor="WHITE">

<H1>
</H1>
<H2></H2>
<BR>
<% Automobile a = (Automobile) request.getAttribute("carModel"); %>
Here is what you selected: 
<TABLE style='text-align: left; width: 40%;' border='1' cellpadding='3' cellspacing='2'>
<TBODY>
  <TR>
    <TD style='vertical-align: top;'><% out.println(a.getMake() + "/" + a.getModel()); %>
    </TD>
    <TD style='vertical-align: top;'>Base Price
    </TD>
    <TD style='vertical-align: top;'><% out.println(a.getBaseprice()); %>
    </TD>
  </TR>
  <%
        Iterator opIter = a.optionSetNamesIterator();
	while (opIter.hasNext()) {
	    String curOpSet = (String) opIter.next();
            String chooseOp = a.getOptionChoice(curOpSet);
	    float  choosePr = a.getOptionChoicePrice(curOpSet);
  %>
  <TR>
    <TD style='vertical-align: top;'><% out.println(curOpSet); %>
    </TD>
    <TD style='vertical-align: top;'><% out.println(chooseOp); %>
    </TD>
    <TD style='vertical-align: top;'><% out.println(choosePr); %>
    </TD>
  </TR>
  <%
	}
  %>
  <TR>
    <TD style='vertical-align: top;'><b>Total Cost</b>
    </TD>
    <TD style='vertical-align: top;'>
    </TD>
    <TD style='vertical-align: top;'><b>$<% out.println(a.getTotalPrice()); %></b>
    </TD>
  </TR>
</TBODY>
</TABLE>
</HTML>
