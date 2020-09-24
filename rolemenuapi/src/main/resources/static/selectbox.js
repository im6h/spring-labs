  $(document).ready(function(){
          $.getJSON("http://localhost:8081/api/v1/roles/1/menus?limit=-1&page=0", function(result){
            var listMenu = [];
             $.each(result.data, function(key,value){
                 if(value.menuStatus === "ACTIVE"){
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
               // Convert data sang tree Object
                var stackData = [];
                stackData.push({
                    id: listMenu[0].id,
                    title: listMenu[0].id + "." + listMenu[0].menuName
                })
                var stackLevel = [];
                stackLevel.push(listMenu[0].menuLevel)
                for(i = 1; i < listMenu.length ; i ++){
                    // Nếu level[i] > level[i-1] hoặc level[i] == level[i-1] thì thêm vào stack
                    if(listMenu[i].menuLevel.split(".").length > listMenu[i-1].menuLevel.split(".").length
                        || listMenu[i].menuLevel.split(".").length === listMenu[i-1].menuLevel.split(".").length){
                            stackData.push({
                                id: listMenu[i].id,
                                title: listMenu[i].id + "." + listMenu[i].menuName
                            })
                            stackLevel.push(listMenu[i].menuLevel)
                    }
                    // nếu level[i] < level[i-1] thì lấy ra cho đến khi nó i stack bằng i hiện tai
                    if(listMenu[i].menuLevel.split(".").length < listMenu[i-1].menuLevel.split(".").length){
                        var listChild = []
                        while(stackLevel[stackLevel.length - 1].split(".").length > listMenu[i].menuLevel.split(".").length){
                            while(stackLevel[stackLevel.length - 1].split(".").length === stackLevel[stackLevel.length - 2].split(".").length){
                               // thêm vào list child
                                stackLevel.pop(stackLevel.length - 1)
                                listChild.push(stackData[stackData.length - 1])
                                stackData.pop(stackData.length - 1)
                            }
                                stackLevel.pop(stackLevel.length - 1)
                                listChild.push(stackData[stackData.length - 1])
                                stackData.pop(stackData.length - 1)

                                stackData[stackData.length - 1].subs = listChild.reverse()
                                listChild = []

                        }
                        stackData.push({
                            id: listMenu[i].id,
                            title: listMenu[i].id + "." + listMenu[i].menuName
                        })
                        stackLevel.push(listMenu[i].menuLevel)
                    }
                }
             console.log(stackData)
             // Add tree Object to
             listMenuJSON = []
             listMenuJSON.push({
                id: 0,
                title: "[Root]",
                subs: stackData
             })
             var comboTree;
             comboTree = $('#menuParentName').comboTree({
                    source : listMenuJSON,
                    isMultiple: false
                });
        });

 });


