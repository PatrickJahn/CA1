


document.getElementById("jokeForm").addEventListener('click', getInfo);

  function getInfo(){
    var url = "https://theonegruppetorulethemall.com/CA1/api/Joke/";
    event.stopPropagation();
    event.preventDefault();
    
    bounce();
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
                 case "id", "random":
                      document.getElementById("tblbody").innerHTML = createHtmlForOne(data);
                     break;
                 case "all":
                      document.getElementById("tblbody").innerHTML = createHtmlForMore(data);   
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
        case "random":
       return "random";
       default: 
           return "";
    }
    
   
}

var createHtmlForOne = function(json){
     var html = "<tr><td>" + json.joke + "</td>"+
            "<td>" + json.reference + "</td>"+
            "<td>: " + json.type +  
            "</td></tr>";
                
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
     
    return genHtml;
};


function bounce(){
    if (event.target.id !== "fid"){
event.stopPropagation();
    event.preventDefault();
  var element =  document.getElementById("hh");
    element.classList.add("bounce");
    console.log(event.target.id);
  setTimeout(function (){
      element.classList.remove("bounce");
  },1000);
    }
}