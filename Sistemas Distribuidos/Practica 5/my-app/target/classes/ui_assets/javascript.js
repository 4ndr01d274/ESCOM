$( document ).ready(function() {
    console.log( "ready!" );

    var button = $("#submit_button");   
    var searchBox = $("#search_text"); 
    var resultsTable = $("#results table tbody"); 
    var resultsWrapper = $("#results"); 

    button.on("click", function(){

        $.ajax({
          method : "POST",
          contentType: "application/json",
          data: createRequest(),
          url: "procesar_datos",
          dataType: "json",
          success: onHttpResponse
          });
      });

    function createRequest() {
        var searchQueryTmp = searchBox.val();

        var frontEndRequest = {
            searchQuery: searchQueryTmp,
        };
        
        return JSON.stringify(frontEndRequest);
    }

    function onHttpResponse(data, status) {
        if (status === "success") {
            console.log(data);
            addResults(data);
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }
    
    function addResults(data) {
        resultsTable.empty();
    
        var cadena = data.cadena;
        resultsWrapper.show();
        resultsTable.append("<tr><td>" + cadena + "</td></tr>");
        resultsWrapper.css("display", "block"); // Asegúrate de que el contenedor esté visible
        
    }
    
    
});

