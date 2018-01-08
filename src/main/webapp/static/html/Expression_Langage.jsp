<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Test des EL sous context/static/html/</title>
</head>

<body>

	<h1>Test des EL sous context/static/html/</h1>

	<p>

 		<% 

	        /* Création d'une liste de légumes et insertion de quatre éléments */	
	        java.util.List<String> legumes = new java.util.ArrayList<String>();	
	        legumes.add( "poireau" );	
	        legumes.add( "haricot" );
	        legumes.add( "carotte");	
	        legumes.add( "pomme de terre" );
	
	        request.setAttribute( "legumes" , legumes );

        %>


        <!-- Les quatre syntaxes suivantes retournent le deuxième élément de la liste de légumes  -->
        ${ legumes.get(1) }<br />
        ${ legumes[1] }<br />
        ${ legumes['1'] }<br />
        ${ legumes["1"] }<br />

	</p>
	
	<p>

        <% 

	        /* Création d'un tableau */
	        String[] animaux = {"chien", "chat", "souris", "cheval"};
	
	        request.setAttribute("animaux" , animaux);

        %>

        

        <!-- Les trois syntaxes suivantes retournent le troisième élément du tableau  -->
        ${ animaux[2] }<br />
        ${ animaux['2'] }<br />
        ${ animaux["2"] }<br />

    </p>
    
    <p>

        <% 

        /* Création d'une Map */
        java.util.Map<String,Integer> desserts = new java.util.HashMap<String, Integer>();
        desserts.put("cookies", 8);
        desserts.put("glaces", 3);
        desserts.put("muffins", 6);
        desserts.put("tartes aux pommes", 2);

        request.setAttribute("desserts" , desserts);

        %>

        

        <!-- Les quatre syntaxes suivantes retournent la valeur associée à la clé "cookies" de la Map de desserts  -->
        ${ desserts.cookies }<br />
        ${ desserts.get("cookies") }<br />
        ${ desserts['cookies'] }<br />
        ${ desserts["cookies"] }<br />

        <% 

        /* Création d'une chaîne nommée "element" et contenant le mot "cookies" */
        String element = "cookies"; 
        request.setAttribute("element",element);

        %>

        <!-- Il est également possible d'utiliser un objet au lieu d'initialiser la clé souhaitée directement dans l'expression -->
        ${ desserts[element] }<br />

    </p>
    
    <p>

       <%

         Map<String, String> aliments = new HashMap<String, String>();
       
         aliments.put( "pomme","fruit" );
         aliments.put( "carotte","légume" );
         aliments.put( "boeuf","viande" );
         aliments.put( "aubergine","légume" );

         request.setAttribute( "aliments", aliments );

       %>

       ${ aliments.pomme } <br /> <!-- affiche fruit -->
       ${ aliments.carotte } <br /> <!-- affiche légume -->
       ${ aliments.boeuf } <br /> <!-- affiche viande -->
       ${ aliments.aubergine } <br /><!-- affiche légume -->

    </p>
    
    <p>

	    <% 
	
		    String paramLangue = request.getParameter("langue");		
		    out.println( "Langue : " + paramLangue );
	
	    %>
	
	    <br />
	
	    <%
	
		    String paramArticle = request.getParameter("article");		
		    out.println( "Article : " + paramArticle );
	
	    %>

    </p>
    
    <p>

    	Langue : ${ param.langue }<br />

    	Article : ${ param.article }

    </p>

</body>

</html>