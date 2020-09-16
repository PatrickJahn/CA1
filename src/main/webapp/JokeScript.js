


document.getElementById("jokeForm").addEventListener('click', getInfo);

  function getInfo(){
    var url = "https://theonegruppetorulethemall.com/CA1/api/Joke/";
    event.stopPropagation();
    event.preventDefault();
    
    
    var getBy = event.target.id;
    
  var urlAddOn = getUrlAddOn(event.target.id);
  url += urlAddOn;
    
fetch(url)
  .then(res => res.json()) //in flow1, just do it
  .then(data => {
   // Inside this callback, and only here, the response data is available
   console.log("data",data);
  /* data now contains the response, converted to JavaScript
     Observe the output from the log-output above
     Now, just build your DOM changes using the data*/   
             switch (getBy) {
                 case "id":
                      document.getElementById("tbl").innerHTML = createHtmlForOne(data);
                     break;
                 case "all":
                      document.getElementById("tbl").innerHTML = createHtmlForMore(data);   
                      break;
                 
                     
             }
         
             
});


    }



function getUrlAddOn(id){
    switch (id) {
        case "id":
          return document.getElementById("fid").value;
       case "all":
       return "all";
       default: 
           return "";
    }
    
   
}

var createHtmlForOne = function(json){
     var html = "<p> <b>Title:</b> " + json.joke + "<br>"+
            "Year: " + json.reference + "<br> "+
            "Genre: " + json.type +  
            "</p>";
                
    return html;
    
};

var createHtmlForMore = function(json){
    // Bruger dårlig form for string builder... 
     var genHtml = "";
    for (obj in json){
   var res =  genHtml.concat("<tr><td>"+json[obj].joke+"</td>"+
           "<td>"+json[obj].reference+"</td>"+
                "<td>"+json[obj].type+"</td></tr>"); 
    genHtml = res;
    }
    
     var table ="<table><thead><tr><th>Joke</th><th>Reference</th><th>Type</th><tbody>" + genHtml + "</tbody></table>" ;
     
    return table;
};
