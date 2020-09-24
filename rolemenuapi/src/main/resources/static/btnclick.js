function submitAdd(){
// Kiểm tra valid()
    if($("#menuName").valid() === false
        || $("#menuSlug").valid() === false
        || $("#menuIcon").valid() === false
        || $("#menuDescription").valid() === false
    ){
        console.log("Form khong hop le")
        return;
    }
    var config = {};
    $("#formAdd").serializeArray().map(function(data) {
        if ( config[data.name] ) {
            if ( typeof(config[data.name]) === "string" ) {
                config[data.name] = [config[data.name]].toString();
            }
            if( config[data.name] )
            config[data.name].push(data.value);
        } else {
            config[data.name] = data.value;
        }
    });
    console.log($("#menuDescription").valid())
    // Chuẩn hóa đầu vào
    config.menuName = config.menuName.trim()
    config.menuSlug = config.menuSlug.trim()
    config.menuIcon= config.menuIcon.trim()
    config.menuDescription = config.menuDescription.trim()
    if(config.menuParentName === ""|| config.menuParentName ==="[Root]"){
        config.menuParentID = 0
    }else{
        var dop =  $("#menuParentName").val().indexOf(".")
        config.menuParentId = $("#menuParentName").val().slice(0,dop)
        var pLevel = listMenu.find(x => x.id == config.menuParentId)
        config.menuLevel = pLevel.menuLevel
    }

    // Tìm menu có tên là ntn
    console.log(JSON.stringify(config))

    $.ajax({
          type : "POST",
          url : "http://localhost:8081/api/v1/roles/1/menus",
          headers : {
             "Content-Type" : "application/json"
          },
          data: JSON.stringify(config),
          success : function(config) {
             alert("Create Success")
          },
          error : function(config) {
            alert(JSON.stringify(config).message)
            document.getElementById("formAdd").reset();
          },
          dataType: 'json',
          contentType: 'application/json;charset=UTF-8'
       });
}

function submitEdit(){
console.log("To Edit")
     $('#exampleModal').on('show.bs.modal', function (event) {
           var modal = $(this)
           modal.find('.modal-body').text("Are you sure Edit ?")
           modal.find('.modal-footer .btn.btn-primary').attr('onclick',`editAgree()`)
        //  modal.find('.modal-title').text('New message to ' + recipient)

        })
    }
function editAgree(){
// Kiểm tra valid()
    if($("#menuName").valid() === false
        || $("#menuSlug").valid() === false
        || $("#menuIcon").valid() === false
        || $("#menuDescription").valid() === false
    ){
        console.log("Form khong hop le")
        return;
    }

    var config = {
      "menuDescription":$("#menuDescription").val(),
      "menuStatus":$("#menuStatus").val(),
      "menuIcon":$("#menuIcon").val(),
      "menuParentId":$("#menuParentName").val(),
      "menuSlug":$("#menuSlug").val(),
      "menuName":$("#menuName").val(),
      "menuLevel":$("#menuParentName").val(),
      "id": $("#menuID").val()
    }
    var id = $("#menuID").val()
      if(config.menuParentId === ""|| config.menuParentName ==="[Root]"){
            config.menuParentId = 0

        }else{
            var dop =  $("#menuParentName").val().indexOf(".")
            config.menuParentId = $("#menuParentName").val().slice(0,dop)
            var pLevel = listMenu.find(x => x.id == config.menuParentId)
            config.menuLevel = pLevel.menuLevel
        }

    console.log(JSON.stringify(config))

    $.ajax({
          type : "PUT",
          url : "http://localhost:8081/api/v1/roles/1/menus/" + id ,
          headers : {
             "Content-Type" : "application/json"
          },
          data: JSON.stringify(config),
          success : function(config) {
                console.log( "Error",JSON.stringify(config.responseJSON.message))
          },
          error : function(config) {
                console.log( "Error",JSON.stringify(config.responseJSON.message))
          },
          dataType: 'json',
          contentType: 'application/json;charset=UTF-8'
       });
}


function submitDelete (id){
    $('#exampleModal').on('show.bs.modal', function (event) {
       var modal = $(this)
       modal.find('.modal-footer .btn.btn-primary').attr('onclick',`deleteAgree(${id})`)
    //  modal.find('.modal-title').text('New message to ' + recipient)
    //  modal.find('.modal-body input').val(recipient)
    })
}
function deleteAgree(id){
    $.ajax({
        url: 'http://localhost:8081/api/v1/roles/1/menus/' + id,
        type: 'DELETE',
        success: function(result) {
            console.log("success")
            window.location = "/setting"
        }
    });
}