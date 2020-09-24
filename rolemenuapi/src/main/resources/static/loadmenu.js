// Load Menu
        var listMenu = [];
  $(document).ready(function(){
     $.getJSON("http://localhost:8081/api/v1/roles/1/menus?limit=-1&page=0", function(result){

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
            listMenu.sort(function(a,b){
                if(a.menuLevel.split(".").length === b.menuLevel.split(".").length){
                    return a.id - b.id
                }
                return a.menuLevel.split(".").length - b.menuLevel.split(".").length
            })

          $.each(listMenu, function(key,value) {
               if(value.menuParentId == 0){
                    $(".nav.nav-pills").append(`
                    <li class = "${value.id} nav-item">
                        <a href= "${value.menuSlug}" class = "nav-link">
                            <i class = "fas fa-circle nav-icon"></i>
                            <p>${value.menuName}</p>
                        </a>
                    </li>`)
               }else{
                   $(`.${value.menuParentId}`).addClass("has-treeview")
                   if($(`.${value.menuParentId} .nav`).length === 0){
                         $(`.${value.menuParentId}`).append(`<ul class = "nav nav-treeview"></ul>`)
                       }
                   $(`.${value.menuParentId}>a`).append(`<i class = "right fas fa-angle-left"></i>`)
                   $(`.${value.menuParentId} .nav`).append(`
                    <li class = "${value.id} nav-item" >
                        <a class = "nav-link"  href= "${value.menuSlug}">
                            <i class = "nav-icon"></i>
                             <p>${value.menuName}</p>
                        </a>
                    </li>`)
               }
          });


      });
    });
