/* global fetch */

console.log("Script running");

var url = "/jpareststarter/api/GroupMember/all";

var liste; 


async function reloadMembers(){
   event.preventDefault();
   destroyTable();
     await fetch(url)
   
            .then(res => res.json()) 
            .then(data => {
               
                console.log("data", data);
        
                liste = data; 
                
            });
   await makeTable(liste); 
}
var $table = $('#table'); 
function makeTable(Liste){ 
    
        $(function () {
            $('#table').bootstrapTable({
        data: Liste
    });
});
event.preventDefault();
}

function destroyTable() {
    $('#table').bootstrapTable("destroy");
}
               
               
               


             

               



            
       


