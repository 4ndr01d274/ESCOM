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
        if (status === "success" ) {
            console.log(data);
            addResults(data);
        } else {
            alert("Error al conectarse al servidor: " + status);
        }
    }

    function addResults(data) {
    resultsTable.empty();

    var top10Libros = data.cadena; // asumo que response contiene el top 10 de libros
    resultsWrapper.show();

    // Construir la tabla con los resultados
    var tableHTML = "<thead><tr><th>Libro</th></tr></thead><tbody>";
    for (var i = 0; i < top10Libros.length; i++) {
        tableHTML += "<tr><td>" + top10Libros[i] + "</td></tr>";
    }
    tableHTML += "</tbody>";

    resultsTable.append(tableHTML);
}
});

