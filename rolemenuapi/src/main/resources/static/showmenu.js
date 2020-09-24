
   $(document).ready(function(){
          $.getJSON("http://localhost:8081/api/v1/roles/1/menus?limit=-1&page=0", function(result){
              var listMenu = [];
                    $.each(result.data, function(key,value){
                          if(value.menuStatus != "DISABLE"){
                           if(value.menuParentId == 0){
                              value.menuLevel = ""+value.id
                           }else{
                              value.menuLevel = value.menuLevel + "." + value.id
                           }
                           listMenu.push(value)
                          }
                      });
                    // Sắp xếp lại list trước khi hiển thị
                  listMenu.sort(function(a,b){
                    var listA = a.menuLevel.split(".")
                        listA[listA.length] = 0
                    var listB = b.menuLevel.split(".")
                        listA[listB.length] = 0

                    for(i = 0 ; i < Math.max(listA.length,listB.length) ; i++){
                        if((listA[i] - listB[i]) != 0){
                            return listA[i] - listB[i]
                        }
                    }
                  })

               $.each(listMenu, function(key,value) {
                    if(value.menuParentId == 0){
                       $("#opened").append(`
                           <tr data-node-id ="${value.id}" class = "simple-tree-table-root simple-tree-table-empty" id ="${value.id}" >
                               <td><span class = "simple-tree-table-handler simple-tree-table-icon" style = "margin-left: 0px"></span>${value.id}</td>
                               <td>${value.menuName}</td>
                               <td>${value.menuSlug}</td>
                               <td>${value.menuStatus}</td>
                               <td class = "btn-group btn-group-sm">
                                   <a href = "/edit/${value.id}" class = "btn btn-info">
                                       <i class = "fas fa-edit"></i>
                                   </a>
                                   <button class = "btn btn-danger" onclick = "submitDelete(${value.id})" data-toggle="modal" data-target="#exampleModal" >
                                       <i class = "fas fa-trash" ></i>
                                   </button>
                               </td>
                           </tr>
                       `)
                   }else{
                       var node_pid = value.menuLevel.replace("."+value.id,"")
                       $("#opened").append(`
                           <tr data-node-id="${value.menuLevel}" data-node-pid = "${node_pid}" class = "simple-tree-table-empty" id="${value.id}">
                               <td><span class = "simple-tree-table-handler simple-tree-table-icon" style = "margin-left: ${30 * (value.menuLevel.split(".").length - 1)}px"></span>${value.id}</td>
                               <td>${value.menuName}</td>
                               <td>${value.menuSlug}</td>
                               <td>${value.menuStatus}</td>
                               <td class = "btn-group btn-group-sm">
                                   <a href = "/edit/${value.id}" class = "btn btn-info">
                                       <i class = "fas fa-edit"></i>
                                   </a>
                                   <button class = "btn btn-danger" onclick = "submitDelete(${value.id})" data-toggle="modal" data-target="#exampleModal" >
                                       <i class = "fas fa-trash"></i>
                                   </button>
                               </td>
                           </tr>
                       `)
                           $("#" + `${value.menuParentId}`).attr("class","simple-tree-table-opened")
                    }
               });

        });

 });



